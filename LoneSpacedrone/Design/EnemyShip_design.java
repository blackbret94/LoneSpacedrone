/* Enemy Ship
 * by Bret Black
 * 
 * These float around the map and try to destroy the space station
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;
import java.applet.*;

public class EnemyShip extends GCompound{
  // instance variables
  public double hSpeed, vSpeed;
  private GImage image;
  public int health, maxHealth;
  private LoneSpacedrone game;
  private Spaceship spaceship;
  public int size;
  private AudioClip fireShot = MediaTools.loadAudioClip("alienLaser.wav");
  public boolean hitByPlayer = false;
  
  // constructor
  public EnemyShip(int size, LoneSpacedrone game, Spaceship spaceship){
    // save instance variables
    
    // draw image
    
    // set speed
    
    // set health
    
    // bring game interface to front
    
  }
  // one time step
  public void oneTimeStep(){
    // move
    
    // check collisions
    
    // randomly change v direction
    
    // randomly shoot
    
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
        
        // damage compartment
        
      }
    }
  }
  
  // take damage
  public void takeDamage(int i){

  }
  
  // check health
  public void checkHealth(){
    if (health <= 0) {
      // destroy this enemy and remove from the arraylist
      for(int i = 0; i < game.enemy.size(); i++){
        if (game.enemy.get(i).equals(this)){
          // create explosion
          
          // award experience and remove
          if (hitByPlayer) {
            // add resources
            
            // gain experience            
            
          }
          
          // remove
          
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
        
        // hit by player
        
      }
    }
    
    // right
    if (game.spaceship.rightLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
         // take damage
        
        // hit by player
        
      }
    }
    
    // up
    if (game.spaceship.upLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // take damage
        
        // hit by player
        
      }
    }
    
    // down
    if (game.spaceship.downLine.isVisible() == true) {
      // find rectangle
      
      if (getBounds().intersects(lineBound)){
        // take damage
        
        // hit by player
        
      }
    }
  }
  
  // shoot bullets
  public void shoot(){
    // add bullet
    
  }
}