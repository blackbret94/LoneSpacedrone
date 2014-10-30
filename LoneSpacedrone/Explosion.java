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
    this.scale = (scale/128);
    this.game = game;
    
    for (int i = 0; i < ring.length; i++) {
      ring[i] = new GOval(1,1);
      add(ring[i]);
    }
    // color rings
    ring[0].setFilled(true);
    ring[0].setFillColor(Color.RED);
    ring[0].setColor(Color.RED);
    ring[1].setFilled(true);
    ring[1].setColor(Color.ORANGE);
    ring[1].setFillColor(Color.ORANGE);
    ring[2].setFilled(true);
    ring[2].setColor(Color.YELLOW);
    ring[2].setFillColor(Color.YELLOW);
    
    game.sendInterfaceFront();
  }
  
  // oneTimeStep
  public void oneTimeStep(){
    // grow rings
    ring[2].setSize(ring[2].getSize().getWidth() + 3*scale,ring[2].getSize().getWidth() + 3*scale);
    ring[2].move(-1.5*scale,-1.5*scale);
    ring[1].setSize(ring[1].getSize().getWidth() + 7*scale,ring[1].getSize().getWidth() + 7*scale);
    ring[1].move(-3.5*scale,-3.5*scale);
    ring[0].setSize(ring[0].getSize().getWidth() + 10*scale,ring[0].getSize().getWidth() + 10*scale);
    ring[0].move(-5*scale,-5*scale);
    
    // run clock
    clock();
  }
  
  // clock
  public void clock(){
    if (clockCount > 0) {
      clockCount--;
    } else {
      destroy();
    }
  }
  
  // death
  public void destroy(){
    for(int i = 0; i < game.explosion.size(); i++){
          if (game.explosion.get(i).equals(this)){
            game.explosion.remove(i);
            setLocation(-50,game.rand.nextInt((int).75*game.APPLICATION_HEIGHT)+game.APPLICATION_HEIGHT/8);
            removeAll();
          }
        }    
  }
}