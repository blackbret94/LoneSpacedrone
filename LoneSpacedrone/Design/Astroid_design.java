/* Astroid
 * by Bret Black
 * 
 * These float around the map and offer resources for the player
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;

public class Astroid extends GCompound {
  // instance variables
  public double hSpeed, vSpeed;
  private GImage image;
  public int health = 15, maxHealth;
  private LoneSpacedrone game;
  private Spaceship spaceship;
  public int size;
  public boolean hitByPlayer = false;
  
  // constructor
  public Astroid(int size, LoneSpacedrone game, Spaceship spaceship){
    // save instance variables
    
    // draw image
    
    // set speed
    
    // set health
    
    // bring interface to front
    
  }
  // one time step
  public void oneTimeStep(){
    // move
    
    // check to see if on map
    
    // check for collisions
    
  }
  
  // check if off map
  public void checkOnMap(){
    if (getX() > game.APPLICATION_WIDTH + getWidth() || getY() > game.APPLICATION_HEIGHT + getHeight()){
      // reset location
    }
  }
  
  // checks for collision with the space station
  public void checkCollisionStation(){
    for (int i = 0; i < game.compartment.length; i++) {
      if (getBounds().intersects(game.compartment[i].getBounds())){
        // take damage
        
        // damage comparmtent
        
      }
    }
  }
  
  // take damage
  public void takeDamage(int i){

  }
  
  // check health
  public void checkHealth(){
    if (health <= 0) {
      // add resources
      if (hitByPlayer) {
        // give resources
        
      }
      
      
      // if size is great than 1, spawn smaller astroids
      if (size > 1){
        // add three smaller astroids
        
      }
      
      // destroy this astroid and remove from the arraylist
      for(int i = 0; i < game.astroid.size(); i++){
        if (game.astroid.get(i).equals(this)){
          // create explosion
          
          // remove
          
          if (hitByPlayer) {
            // give experience
            
          }
        }
      }    
    }
  }
  
  // check for collision with lazers
  public void checkCollisionLazer(){
    // left 
    // check
    if (game.spaceship.leftLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // take damage
        
        // indicate that it was hit by the player
        
      }
    }
    
    // right
    if (game.spaceship.rightLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // take damage
        
        // indicate that it was hit by the player
        
      }
    }
    
    // up
    if (game.spaceship.upLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // take damage
        
        // indicate that it was hit by the player
        
      }
    }
    
    // down
    if (game.spaceship.downLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // take damage
        
        // indicate that it was hit by the player
        
      }
    }
  }
}