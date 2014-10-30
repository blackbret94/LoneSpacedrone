/* Bullet
 * by Bret Black
 * 
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;

public class Bullet extends GCompound {
  // instance variables
  private GImage image;
  private int speed;
  private LoneSpacedrone game;
  private Spaceship spaceship;
  public boolean alive = true;
  
  // constructor
  public Bullet(int speed, LoneSpacedrone game, Spaceship spaceship) {
    // save instance variables
    
    // draw image
    
    // sent interface to front
  }
  // oneTimeStep
  public void oneTimeStep(){
    // move
    
    // check collisions
    
    // destroy if not alive
  }
  
  // check if off map
  public void checkOnMap(){
    if (getX() > game.APPLICATION_WIDTH + getWidth() || getY() > game.APPLICATION_HEIGHT + getHeight()){
      // destroy
      
    }
  }
  
  // checks for collision with the space station
  public void checkCollisionStation(){
    for (int i = 0; i < game.compartment.length; i++) {
      if (getBounds().intersects(game.compartment[i].getBounds())){
        // do damage to compartment
        
        // destroy
      }
    }
  }
  
  // die
  public void destroy(){
    for(int i = 0; i < game.bullet.size(); i++){
      if (game.bullet.get(i).equals(this)){
        // create explosion
        
        // remove
        
      }
    }  
  }
}