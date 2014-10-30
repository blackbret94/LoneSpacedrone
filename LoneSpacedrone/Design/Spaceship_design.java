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
    
    // draw oval
    
    // draw lasers
    
    // save instance variables

  }
  
  // oneTimeStep
  public void oneTimeStep(){
    // check if on map
    
    // check health
    
    // color lasers
    if (rightLine.isVisible()) {
      // give laser a random color
      
    }
    
    if (leftLine.isVisible()) {
      // give laser a random color
      
    }
    
    if (upLine.isVisible()) {
      // give laser a random color
      
    }
    
    if (downLine.isVisible()) {
      // give laser a random color
      
    }
    
    if (!canFireAoe) AoeClock();
    
    // respond to AOE
    if (aoeActive) {
      if (.5*aoeOval.getSize().getWidth() < .5*range) {
        // make oval larger
        
      } else {
        // reset oval
        
      }
    }
  }
  
  // check if off map
  public void checkOnMap(){
    if (getX() > game.APPLICATION_WIDTH + getWidth() || getY() > game.APPLICATION_HEIGHT + getHeight() || 
        getX() <  -getWidth() || getY() <  -getHeight()){
      // reset location
      
    }
  }
  
  // reset it health is 0
  public void checkHealth(){
    if (game.playerHealth <= 0){
      if (game.lives > 0) {
        // reset location
        
        // deduct lives
        
      }
    }
  }
  
  // take damage if collision with astroid
  public void checkAstroidCollision(int i){
    if (game.astroid.get(i).getBounds().intersects(this.getBounds())) {
      // take damage
      
      // destroy astroid
      
    }
  }
  
  // check enemy collision
  public void checkEnemyCollision(int i){
    if (game.enemy.get(i).getBounds().intersects(this.getBounds())) {
      // take damage
      
      // destroy enemy
      
    }
  }
  
  // check bullet collision
  public void checkBulletCollision(int i){
    if (game.bullet.get(i).getBounds().intersects(this.getBounds())) {

    }
  }
  
  // update lasers
  public void updateLines(){

  }
  
  public void AoeClock(){
    if (aoeTimer > 0) {
      // count down
      
    } else {
      // change button image
      
    }
  }
  
  // draw more sophisticated lazers
  private Color colorLasers(){
    switch (game.rand.nextInt(3)) {
      case(0):
        // return orange
        
      case(1):
        // return red
        
      case(2):
        // return yellow
        
      default:
        // return white
    }
  }
}