/* Spaceship
 * by Bret Black
 * 
 * This is the player character
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;

public class Spaceship extends GCompound {
  // instance variables
  public GImage spaceshipImage;
  private LoneSpacedrone game;
  public int range = 300;
  public GOval aoeOval;
  public GLine leftLine,rightLine,upLine,downLine;
  public int aoeTimer, aoeTimerMax = 300;
  public boolean aoeActive = false, canFireAoe = true;
  
  // constructor
  public Spaceship(LoneSpacedrone game) {
    // add spaceship image
    spaceshipImage = new GImage("spaceShip2.png");
    //spaceshipImage.scale(2);
    add(spaceshipImage);
    
    // draw oval
    aoeOval = new GOval(spaceshipImage.getWidth()/2,spaceshipImage.getHeight()/2,0,0);
    aoeOval.setColor(Color.WHITE);
    aoeOval.setVisible(false);
    add(aoeOval);
    aoeOval.sendToBack();
    
    // draw lines
    leftLine = new GLine(0,getHeight()/2,-range,getHeight()/2);
    leftLine.setColor(Color.YELLOW);
    leftLine.setVisible(false);
    add(leftLine);
    
    rightLine = new GLine(getWidth(),getHeight()/2,getWidth()+range,getHeight()/2);
    rightLine.setColor(Color.YELLOW);
    rightLine.setVisible(false);
    add(rightLine);
    
    upLine = new GLine(getWidth()/2,0,getWidth()/2,-range);
    upLine.setColor(Color.YELLOW);
    upLine.setVisible(false);
    add(upLine);
    
    downLine = new GLine(getWidth()/2,getHeight(),getWidth()/2,getHeight()+range);
    downLine.setColor(Color.YELLOW);
    downLine.setVisible(false);
    add(downLine);
    
    // deal with variables
    aoeTimer = aoeTimerMax;
    
    this.game = game;
  }
  
  // oneTimeStep
  public void oneTimeStep(){
    checkOnMap();
    checkHealth();
    
    // color lasers
    if (rightLine.isVisible()) {
      rightLine.setColor(colorLasers());
    }
    
    if (leftLine.isVisible()) {
      leftLine.setColor(colorLasers());
    }
    
    if (upLine.isVisible()) {
      upLine.setColor(colorLasers());
    }
    
    if (downLine.isVisible()) {
      downLine.setColor(colorLasers());
    }
    
    if (!canFireAoe) AoeClock();
    
    // respond to AOE
    if (aoeActive) {
      if (.5*aoeOval.getSize().getWidth() < .5*range) {
        aoeOval.setSize(aoeOval.getSize().getWidth() + 5,aoeOval.getSize().getWidth() + 5);
        aoeOval.move(-2.5,-2.5);
      } else {
        aoeOval.setSize(0,0);
        aoeOval.setVisible(false);
        aoeActive = false;
        aoeOval.setLocation(getWidth()/2,getHeight()/2);
      }
    }
  }
  
  // check if off map
  public void checkOnMap(){
    if (getX() > game.APPLICATION_WIDTH + getWidth() || getY() > game.APPLICATION_HEIGHT + getHeight() || 
        getX() <  -getWidth() || getY() <  -getHeight()){
      this.setLocation(game.APPLICATION_WIDTH/2,game.APPLICATION_HEIGHT/2);
    }
  }
  
  // reset it health is 0
  public void checkHealth(){
    if (game.playerHealth <= 0){
      if (game.lives > 0) {
        this.setLocation(4*game.APPLICATION_WIDTH/5,game.APPLICATION_HEIGHT/2);
        game.playerHealth = game.maxPlayerHealth;
        game.lives--;
      } else game.gameOver = true;
    }
  }
  
  // take damage if collision with astroid
  public void checkAstroidCollision(int i){
    if (game.astroid.get(i).getBounds().intersects(this.getBounds())) {
      if (!aoeActive) game.playerHealth -= 5*game.astroid.get(i).size;
      game.astroid.get(i).health = 0;
      game.astroid.get(i).hitByPlayer = true;
    }
  }
  
  // check enemy collision
  public void checkEnemyCollision(int i){
    if (game.enemy.get(i).getBounds().intersects(this.getBounds())) {
      if (!aoeActive) game.playerHealth -= 10;
      game.enemy.get(i).health = 0;
      game.enemy.get(i).hitByPlayer = true;
    }
  }
  
  // check bullet collision
  public void checkBulletCollision(int i){
    if (game.bullet.get(i).getBounds().intersects(this.getBounds())) {
      if (!aoeActive) game.playerHealth -= 10;
      game.bullet.get(i).alive = false;
    }
  }
  
  // update lines
  public void updateLines(){
    leftLine.setEndPoint(-range,getHeight()/2);
    rightLine.setEndPoint(getWidth()+range,getHeight()/2);
    upLine.setEndPoint(getWidth()/2,-range);
    downLine.setEndPoint(getWidth()/2,getHeight()+range);
  }
  
  public void AoeClock(){
    if (aoeTimer > 0) {
      aoeTimer--;
    } else {
      game.sonicBoomButton.setImage("greenButton.png");
      aoeTimer = aoeTimerMax;
      canFireAoe = true;
    }
  }
  
  // draw more sophisticated lazers
  private Color colorLasers(){
    switch (game.rand.nextInt(3)) {
      case(0):
        return Color.ORANGE;
        
      case(1):
        return Color.RED;
        
      case(2):
        return Color.YELLOW;
        
      default:
        return Color.WHITE;
    }
  }
}