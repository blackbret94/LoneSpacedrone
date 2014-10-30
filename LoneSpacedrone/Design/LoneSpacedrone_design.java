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
    playerHealth, maxPlayerHealth, resources, aliensToSpawn, lives = 3;
  public Spaceship spaceship;
  private GLabel resourceText, levelText, livesText, sonicText, healthText;
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
    // setup key listeners and title

    
    // draw stars, set counters to x0 and y0
    
    // draw image
    while (drawY < APPLICATION_HEIGHT){
      while (drawX < APPLICATION_WIDTH){
        // declare image, add, and move xcounter
      }
      // reset xCounter and move yCounter
      
    }
    
    // set up health
    
    // add spaceship

    
    
    // ** NEW SPACESTATION **
    // set up counter and declare the first set of compartments
    
    for (int i = 5; i <11; i++) {
      // declare sheild compartments

    }
    
    // add compartments
    for (int i = 0; i < 5; i++) {
      // add compartment and move counter
    }
    
    // add sheilds
    
    // bring player back to front
    
    // color background

    // draw interface
    
    // set up waves
    
    // set up clock

  }
  
  // run
  public void run(){
    while (!gameOver){
      // run this oneTimeStep
      
      // pause
      
    }
  }
  
  // oneTimeStep
  private void oneTimeStep(){
    // move spaceship
    
    // run others
    // run astroids
    for (int i = 0; i < astroid.size(); i++){

    }
    
    // spaceship
    
    // space station
    for (int i = 0; i < compartment.length; i++){

    }
    
    // update text 
    
    // run clock
    
    // spawn astroids
    
    // run aliens
    for (int i = 0; i < enemy.size(); i++){

    }
    
    // run bullets
    for (int i = 0; i < bullet.size(); i++){

    }
    
    // run explosions
    for (int i = 0; i < explosion.size(); i++) {

    }
    
    // check for level up
    if (experience >= experienceNeeded){
      
    }
  }
  
  //respond to input
  // key press
  public void keyPressed (KeyEvent e){
    
    // check which key was pressed
    
    // left
    if (keyPressed == KeyEvent.VK_LEFT) {
      if (hSpeed > -maxSpeed) {
        // change speed

      }
    }
    // right
    if (keyPressed == KeyEvent.VK_RIGHT) {
      if (hSpeed < maxSpeed) {
        // change speed
        
      }
    }
    // up
    if (keyPressed == KeyEvent.VK_UP) {
      if (vSpeed > -maxSpeed) {
        // change speed
        
      }
    }
    // down
    if (keyPressed == KeyEvent.VK_DOWN) {
      if (vSpeed < maxSpeed) {
        // change speed
        
      }
    }
    
    // shoot lazer beams
    //left
    if (keyPressed == KeyEvent.VK_A){
      // make laser visible
      
    }
    
    // right
    if (keyPressed == KeyEvent.VK_D){
      // make laser visible
      
    }
    
    // up
    if (keyPressed == KeyEvent.VK_W){
      // make laser visible
      
    }
    
    // down
    if (keyPressed == KeyEvent.VK_S){
      // make laser visible
      
    }
    
    // AOE
    if (keyPressed == KeyEvent.VK_SPACE){
     // shoot sonic boom
    }
  }
  
  // key released
  public void keyReleased (KeyEvent e){
    // determine which key was pressed
    
    // shoot lazer beams
    //left
    if (keyPressed == KeyEvent.VK_A){
      // hide laser
      
    }
    
    // right
    if (keyPressed == KeyEvent.VK_D){
      // hide laser
      
    }
    
    // up
    if (keyPressed == KeyEvent.VK_W){
      // hide laser
      
    }
    
    // down
    if (keyPressed == KeyEvent.VK_S){
      // hide laser
      
    }
  }
  
  // draw interface
  private void drawInterface(){
    // **DRAW INTERFACE**
    // draw top line
    
    // draw backing
    
    // ** DRAW EXPERIENCE BAR **
    // black bottom
    
    // moving counter
    
    // top outline
    
    // lines 
    for (int i = 0; i < xpLine.length; i++){
      
    }
    
    // level text

    
    // ** DRAW HEALTH BAR **
    // text
    
    // save start of bar
    
    // black bottom
    
    // moving counter
    
    // top outline
    
    // ** ADDITIONAL INTERFACE **
    // resources
    
    // lives
    
    // button
    
    // button text
    
  }
  
  // update labels
  private void updateLabels(){

  }
  
  // astroid attack
  public void spawnAstroids(){
    if (rand.nextInt(100 - level*2) == 1) {
      // create an astroid
      
    }
  }
  
  // AOE attack
  public void shootAOE(){
    if (spaceship.canFireAoe) {
      // fire AOE
      
      // change icon

    }
  }
  
  // alien clock
  public void alienClock(){
    if (alienClockCount > 0) {
      // count down
      
    } else {
      // enable attack
      
      // set number of aliens
      
      // reset clock

    }
  }
  
  // level up
  public void levelUp(){
    // increase level and appropriate stats

  }
  
  // check to see if the game has ended
  public void checkLoss(){
    for (int i = 0; i < compartment.length; i++) {
      if (compartment[i].alive) return;
    }
    // set game to over
    
  }
  
  // spawn enemy units
  public void spawnAliens(){
    if (rand.nextInt(50 - level) == 1) {
      // spawn enemy
      
      if (aliensToSpawn <= 0) alienAttack = false;
    }
  }
  
  // send interface to front
  public void sendInterfaceFront(){
    // GRects
    
    // GLabels
    
    // GLines
    for (int i = 0; i < xpLine.length; i++){

    }
    
    // GImages
    
  }
  
  // add explosion
  public void addExplosion(double scale, double x, double y){

  }
}