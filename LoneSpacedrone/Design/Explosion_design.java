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

public class Explosion extends GCompound {
  // instance variables
  private int clockCount = 15;
  private double scale;
  private GOval[] ring = new GOval[3];
  private LoneSpacedrone game;
  
  // constructor
  public Explosion(double scale, LoneSpacedrone game){
    // save instance variables
    
    for (int i = 0; i < ring.length; i++) {
      // add ring
      
    }
    // color rings
    
    // bring the game interface to the front
    
  }
  
  // oneTimeStep
  public void oneTimeStep(){
    // grow rings
    
    // run clock

  }
  
  // clock
  public void clock(){
    if (clockCount > 0) {
      // count down
      
    } else {
      // destroy
      
    }
  }
  
  // death
  public void destroy(){
    for(int i = 0; i < game.explosion.size(); i++){
          if (game.explosion.get(i).equals(this)){
            // remove
            
          }
        }    
  }
}