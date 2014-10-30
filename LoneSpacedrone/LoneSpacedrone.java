/* Lone SpaceDrone
 * by Bret Black
 * 
 * In this game, the player flies around space gathering resources and building an encampment
 * 
 * 
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.applet.*;

public class LoneSpacedrone extends GraphicsProgram {
  // constants
  public static final int 
    DELAY = 20,// framerate
    APPLICATION_WIDTH = 1200,  //Width of the window
    APPLICATION_HEIGHT = 800; //Height of the window
  
  // instance variables
  public double hSpeed = 0, vSpeed = 0, maxSpeed = 2;
  public double engineSpeed = .2; // acceleration
  public int clockCount, clockMax, alienClockCount = DELAY*45, alienClockMax = DELAY * 180, wave, experience = 0, level = 1, experienceNeeded = 200,
    playerHealth, maxPlayerHealth, resources, aliensToSpawn, lives = 2;
  public Spaceship spaceship;
  private GLabel resourceText, levelText, livesText, sonicText, healthText, gameOverText;
  private GRect levelBar,healthBar, underBoundaryHealth, topBoundaryHealth, topBoundary, underBoundary, backing, topLine;
  public GImage sonicBoomButton;
  public boolean gameOver = false, resting = false, alienAttack = false;
  public RandomGenerator rand = new RandomGenerator();
  public Compartment healComp, lazerComp, armorComp;
  
  // arrays
  public ArrayList<Astroid> astroid = new ArrayList<Astroid>();
  public ArrayList<EnemyShip> enemy = new ArrayList<EnemyShip>();
  public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
  public ArrayList<Explosion> explosion = new ArrayList<Explosion>();
  public Compartment[] compartment = new Compartment[11];
  public GLine[] xpLine = new GLine[40];
  
  // init
  public void init() {
    // setup
    addKeyListeners(this);
    setTitle("Lone Spacedrone by Bret Black");
    
    // draw stars
    int drawX = 0;
    int drawY = 0;
    
    // draw image
    while (drawY < APPLICATION_HEIGHT){
      while (drawX < APPLICATION_WIDTH){
        // declare image and scale
        GImage stars = new GImage("space.png");
        
        add(stars,drawX,drawY);
        drawX += 512;
      }
      drawX = 0;
      drawY += 512;
    }
    
    // stats
    maxPlayerHealth = 100;
    playerHealth = maxPlayerHealth;
    
    // add spaceship
    spaceship = new Spaceship(this);
    add(spaceship,4*APPLICATION_WIDTH/5,APPLICATION_HEIGHT/2);
    
    
    // ** NEW SPACESTATION **
    int place = 32;
    compartment[0] = new Compartment(Compartment.Type.OBSERVE,spaceship,this);
    compartment[1] = new Compartment(Compartment.Type.CONNECT,spaceship,this);
    compartment[2] = new Compartment(Compartment.Type.OBSERVE,spaceship,this);
    compartment[3] = new Compartment(Compartment.Type.CONNECT,spaceship,this);
    compartment[4] = new Compartment(Compartment.Type.OBSERVE,spaceship,this);
    
    for (int i = 5; i <11; i++) {
      compartment[i] = new Compartment(Compartment.Type.SHIELD,spaceship,this);
    }
    
    // add
    int drawPlace = 32;
    for (int i = 0; i < 5; i++) {
      add(compartment[i],APPLICATION_WIDTH-compartment[i].getWidth(),drawPlace);
      drawPlace += 128;
    }
    
    // add sheilds
    add(compartment[5],APPLICATION_WIDTH-384,32);
    add(compartment[6],APPLICATION_WIDTH-384-128,32+128);
    add(compartment[7],APPLICATION_WIDTH-384,32+256);
    add(compartment[8],APPLICATION_WIDTH-384-256,32+256);
    add(compartment[9],APPLICATION_WIDTH-384-128,32+256+128);
    add(compartment[10],APPLICATION_WIDTH-384,32+512);
    
    // bring player back to front
    spaceship.sendToFront();
    
    // color background
    setBackground(Color.BLACK);
    
    drawInterface();
    
    // set up waves
    wave = 0;
    resting = false;
    
    // set up clock
    clockMax = DELAY*30;
    clockCount = clockMax;
  }
  
  // run
  public void run(){
    while (!gameOver){
      // run this
      oneTimeStep();
      
      // pause
      pause(DELAY);
    }
  }
  
  // oneTimeStep
  private void oneTimeStep(){
    spaceship.move(hSpeed,vSpeed);
    // run others
    // astroids
    for (int i = 0; i < astroid.size(); i++){
      spaceship.checkAstroidCollision(i);
      astroid.get(i).oneTimeStep();
    }
    
    // spaceship
    spaceship.oneTimeStep();
    
    // space station
    for (int i = 0; i < compartment.length; i++){
      compartment[i].oneTimeStep();
    }
    
    // update text 
    updateLabels();
    
    // run clock
    alienClock();
    if (alienAttack) spawnAliens();
    
    // spawn astroids
    spawnAstroids();
    
    // run aliens
    for (int i = 0; i < enemy.size(); i++){
      spaceship.checkEnemyCollision(i);
      enemy.get(i).oneTimeStep();
    }
    
    // run bullets
    for (int i = 0; i < bullet.size(); i++){
      spaceship.checkBulletCollision(i);
      bullet.get(i).oneTimeStep();
    }
    
    // run explosions
    for (int i = 0; i < explosion.size(); i++) {
      explosion.get(i).oneTimeStep();
    }
    
    // check for level up
    if (experience >= experienceNeeded){
      levelUp();
    }
    
    // show gameOver text
    if (gameOver && !gameOverText.isVisible()){
      gameOverText.sendToFront();
      gameOverText.setVisible(true);
    }
  }
  
  //respond to input
  // key press
  public void keyPressed (KeyEvent e){
    
    // check which key was pressed
    int keyPressed = e.getKeyCode();
    
    // left
    if (keyPressed == KeyEvent.VK_LEFT) {
      if (hSpeed > -maxSpeed) {
        hSpeed -= engineSpeed;
      }
    }
    // right
    if (keyPressed == KeyEvent.VK_RIGHT) {
      if (hSpeed < maxSpeed) {
        hSpeed += engineSpeed;
      }
    }
    // up
    if (keyPressed == KeyEvent.VK_UP) {
      if (vSpeed > -maxSpeed) {
        vSpeed -= engineSpeed;
      }
    }
    // down
    if (keyPressed == KeyEvent.VK_DOWN) {
      if (vSpeed < maxSpeed) {
        vSpeed += engineSpeed;
      }
    }
    
    // shoot lazer beams
    //left
    if (keyPressed == KeyEvent.VK_A){
      spaceship.leftLine.setVisible(true);
    }
    
    // right
    if (keyPressed == KeyEvent.VK_D){
      spaceship.rightLine.setVisible(true);
    }
    
    // up
    if (keyPressed == KeyEvent.VK_W){
      spaceship.upLine.setVisible(true);
    }
    
    // down
    if (keyPressed == KeyEvent.VK_S){
      spaceship.downLine.setVisible(true);
    }
    
    // AOE
    if (keyPressed == KeyEvent.VK_SPACE){
      shootAOE();
      //pulseSound.play();
    }
  }
  
  // key released
  public void keyReleased (KeyEvent e){
    // determine which key was pressed
    int keyPressed = e.getKeyCode();
    
    // shoot lazer beams
    //left
    if (keyPressed == KeyEvent.VK_A){
      spaceship.leftLine.setVisible(false);
    }
    
    // right
    if (keyPressed == KeyEvent.VK_D){
      spaceship.rightLine.setVisible(false);
    }
    
    // up
    if (keyPressed == KeyEvent.VK_W){
      spaceship.upLine.setVisible(false);
    }
    
    // down
    if (keyPressed == KeyEvent.VK_S){
      spaceship.downLine.setVisible(false);
    }
  }
  
  // draw interface
  private void drawInterface(){
    // **DRAW INTERFACE**
    // draw top line
    topLine = new GRect(0,APPLICATION_HEIGHT-85,APPLICATION_WIDTH,5);
    topLine.setFilled(true);
    topLine.setColor(Color.lightGray);
    add(topLine);
    
    // draw backing
    backing = new GRect(0,APPLICATION_HEIGHT-80,APPLICATION_WIDTH,60);
    backing.setFilled(true);
    backing.setColor(Color.darkGray);
    add(backing);
    
    // ** DRAW EXPERIENCE BAR **
    // black bottom
    underBoundary = new GRect(-1,APPLICATION_HEIGHT-40,APPLICATION_WIDTH+1,20);
    underBoundary.setFilled(true);
    underBoundary.setColor(Color.BLACK);
    add(underBoundary);
    
    // moving counter
    levelBar = new GRect(0,APPLICATION_HEIGHT-40,0,20);
    levelBar.setFilled(true);
    levelBar.setColor(Color.ORANGE);
    add(levelBar);
    
    // top outline
    topBoundary = new GRect(0,APPLICATION_HEIGHT-40,APPLICATION_WIDTH,20);
    topBoundary.setColor(Color.darkGray);
    topBoundary.setFilled(false);
    add(topBoundary);
    
    // lines 
    for (int i = 0; i < xpLine.length; i++){
      xpLine[i] = new GLine(i*(APPLICATION_WIDTH/40),APPLICATION_HEIGHT-40,i*(APPLICATION_WIDTH/40),APPLICATION_HEIGHT-20);
      xpLine[i].setColor(Color.darkGray);
      add(xpLine[i]);
    }
    
    // level text
    levelText = new GLabel("Level: " + level,APPLICATION_WIDTH/2,APPLICATION_HEIGHT-26);
    levelText.setColor(Color.WHITE);
    add(levelText);
    
    // ** DRAW HEALTH BAR **
    // text
    healthText = new GLabel("Health: ",16,APPLICATION_HEIGHT-50);
    healthText.setColor(Color.WHITE);
    add(healthText);
    
    // save start of bar
    int startHealthBar = 16+(int)healthText.getWidth();
    
    // black bottom
    underBoundaryHealth = new GRect(startHealthBar,APPLICATION_HEIGHT-60,100,12);
    underBoundaryHealth.setFilled(true);
    underBoundaryHealth.setColor(Color.BLACK);
    add(underBoundaryHealth);
    
    // moving counter
    healthBar = new GRect(startHealthBar,APPLICATION_HEIGHT-60,0,12);
    healthBar.setFilled(true);
    healthBar.setColor(Color.RED);
    add(healthBar);
    
    // top outline
    topBoundaryHealth = new GRect(startHealthBar,APPLICATION_HEIGHT-60,100,12);
    topBoundaryHealth.setColor(Color.WHITE);
    topBoundaryHealth.setFilled(false);
    add(topBoundaryHealth);
    
    // ** ADDITIONAL INTERFACE **
    // resources
    resourceText = new GLabel("Resources: " + resources, startHealthBar + 116,APPLICATION_HEIGHT-50);
    resourceText.setColor(Color.WHITE);
    add(resourceText);
    
    // lives
    livesText = new GLabel("Lives: " + lives, startHealthBar+116+resourceText.getWidth()+32,APPLICATION_HEIGHT-50);
    livesText.setColor(Color.WHITE);
    add(livesText);
    
    // button
    sonicBoomButton = new GImage("greenButton.png",APPLICATION_WIDTH-400,APPLICATION_HEIGHT-76);
    add(sonicBoomButton);
    
    // button text
    sonicText = new GLabel("PRESS SPACE TO FIRE A SONIC BOOM",APPLICATION_WIDTH-400+sonicBoomButton.getWidth()+16,APPLICATION_HEIGHT-55);
    sonicText.setColor(Color.WHITE);
    add(sonicText);
    
    //  gameOver text
    gameOverText = new GLabel ("YOU'VE FAILED! TRY AGAIN.");
    gameOverText.setColor(Color.RED);
    gameOverText.setVisible(false);
    gameOverText.setFont("SansSerif-Bold-64");
    add(gameOverText,APPLICATION_WIDTH/2-gameOverText.getWidth()/2,APPLICATION_HEIGHT/2-gameOverText.getHeight()/2);
  }
  
  // update labels
  private void updateLabels(){
    resourceText.setLabel("Resources: " + resources);
    levelText.setLabel("Level: " + level);
    livesText.setLabel("Lives: " + lives);
    levelBar.setSize(APPLICATION_WIDTH*((double)experience/(double)experienceNeeded),20);
    healthBar.setSize(100*((double)playerHealth/(double)maxPlayerHealth),12);
  }
  
  // astroid attack
  public void spawnAstroids(){
    if (rand.nextInt(100 - level*2) == 1) {
      Astroid newAstroid = new Astroid(rand.nextInt(2)+1, this, spaceship);
      astroid.add(newAstroid);
      this.add(newAstroid,-newAstroid.getWidth()*1.5,rand.nextInt((int).66*APPLICATION_HEIGHT)+APPLICATION_HEIGHT/4);
    }
  }
  
  // AOE attack
  public void shootAOE(){
    if (spaceship.canFireAoe) {
      spaceship.aoeActive = true;
      spaceship.aoeOval.setVisible(true);
      spaceship.canFireAoe = false;
      
      // change icon
      sonicBoomButton.setImage("redButton.png");
    }
  }
  
  // alien clock
  public void alienClock(){
    if (alienClockCount > 0) {
      // count down
      alienClockCount--;
    } else {
      // enable attack
      alienAttack = true;
      
      // set number of aliens
      aliensToSpawn = 15 + level*3;
      
      // reset clock
      alienClockCount = alienClockMax;
    }
  }
  
  // level up
  public void levelUp(){
    level++;
    spaceship.range = 300 + 50*level;
    experience = 0;
    experienceNeeded = (int)(experienceNeeded * 1.30);
    maxSpeed += .3;
    engineSpeed += .02;
    maxPlayerHealth += 10;
    playerHealth = maxPlayerHealth;
    if (level == 5 || level == 10 || level == 15 || level == 20){
      lives++;
    }
  }
  
  // check to see if the game has ended
  public void checkLoss(){
    for (int i = 0; i < compartment.length; i++) {
      if (compartment[i].alive) return;
    }
    gameOver = true;
  }
  
  // spawn enemy units
  public void spawnAliens(){
    if (rand.nextInt(50 - level) == 1) {
      aliensToSpawn--;
      EnemyShip newEnemy = new EnemyShip(1, this, spaceship);
      enemy.add(newEnemy);
      this.add(newEnemy,-30,rand.nextInt(APPLICATION_HEIGHT/3)+APPLICATION_HEIGHT/3);
      
      if (aliensToSpawn <= 0) alienAttack = false;
    }
  }
  
  // keep interface on top
  public void sendInterfaceFront(){
    // GRects
    backing.sendToFront();
    topLine.sendToFront();
    underBoundary.sendToFront();
    levelBar.sendToFront();
    topBoundary.sendToFront();
    underBoundaryHealth.sendToFront();
    healthBar.sendToFront();
    topBoundaryHealth.sendToFront();
    
    // GLabels
    resourceText.sendToFront();
    levelText.sendToFront();
    livesText.sendToFront();
    sonicText.sendToFront();
    healthText.sendToFront();
    
    // GLines
    for (int i = 0; i < xpLine.length; i++){
      xpLine[i].sendToFront();
    }
    
    // GImages
    sonicBoomButton.sendToFront();
  }
  
  // add explosion
  public void addExplosion(double scale, double x, double y){
    Explosion newExplosion = new Explosion(scale, this);
    explosion.add(newExplosion);
    add(newExplosion, x, y);
  }
}