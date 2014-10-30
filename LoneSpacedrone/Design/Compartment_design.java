/* Compartment
 * by Bret Black
 * 
 * These are parts of the spaceship that the player can interact with
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;

public class Compartment extends GCompound {
  // enum
  public enum Type {
    OBSERVE, CONNECT, SHIELD;
  }
  public Type type;
  private Spaceship spaceship;
  private LoneSpacedrone game;
  private GRect backRect, fillRect, healthFill, healthFrame;
  public int health, maxHealth, resourceClockCount = 1000, resourceClockMax = 1000;
  public GImage sprite, resourceImage, healthImage;
  private double progress = 0;
  public boolean alive = true;
  
  // constructor
  public Compartment(Type type, Spaceship spaceship, LoneSpacedrone game){
    // save instance variables
    
    // health
    
    switch(type) {
      // observatory
      case OBSERVE:
        // draw image
        
        // initilize health
        
        break;
        
        // connector
      case CONNECT:
        // draw image
        
        // initilize health
        
        break;
        
      case SHIELD:
        // draw image
        
        // initilize health
        
        break;
    }
    
    // add powerup images
    
  }
  
  // oneTimeStep
  public void oneTimeStep(){
    if (alive) {
      // check collision
      
      // update health
      
      if (type == Type.OBSERVE) {
        // spawn resource
        
        if (healthImage.isVisible() || resourceImage.isVisible()){
          // run the resource clock
          
        }
      }
    }
  }
  
// check for collision with lazers
  public void checkCollisionLazer(){
    // left 
    // check
    if (spaceship.leftLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // repair
        
      }
    }
    
    // right
    if (game.spaceship.rightLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // repair
        
      }
    }
    
    // up
    if (game.spaceship.upLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // repair
        
      }
    }
    
    // down
    if (game.spaceship.downLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // repair
        
      }
    }
  }
  
  private void drawBoxes() {

  }
  // draw health bar
  private void initHealth(){
    // draw the red part of the health bar
    
    // draw the outline of the health bar

  }
  
  // update health bar upon hit
  public void updateHealth(){
    
  }
  
  // dies if health is too low
  private void checkHealth(){
    if (health <= 0) {
      // remove from list
      
      // set off screen
    }
  }
  
  // repair the station and collect boosts
  private void repair(){
    // repair
    if (game.resources >= 3 && health < maxHealth) {
      // detract resources
      
      // increase health
    }
    
    // collect boosts
    if (type == Type.OBSERVE && (resourceImage.isVisible() || healthImage.isVisible())){
      if (resourceImage.isVisible()){
        // add resources
        
        // hide image
        
      } else {
        // increase health
        
        // hide image
        
      }
    }
  }
  
  // add collectable resources  
  private void spawnResource(){
    if (!resourceImage.isVisible() & !healthImage.isVisible()){
      if (game.rand.nextInt(3000) == 0) {
        if (game.rand.nextInt(2) == 0){
          // show health image
          
        } else {
          // show resource image
          
        }
      }
    }
  }
  
  // resource clock
  public void resourceClock(){
    if (resourceClockCount > 0) {
      // count down
      
    } else {
      // hide images
      
      // reset clock

    }
  }
}