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
    this.game = game;
    this.spaceship = spaceship;
    this.speed = speed;
    
    // draw image
    image = new GImage("fireball.png");
    //image.scale(size);
    add(image);
    
    game.sendInterfaceFront();
  }
  // oneTimeStep
  public void oneTimeStep(){
    move(speed,0);
    checkOnMap();
    checkCollisionStation();
    
    if (!alive) destroy();
  }
  
  // check if off map
  public void checkOnMap(){
    if (getX() > game.APPLICATION_WIDTH + getWidth() || getY() > game.APPLICATION_HEIGHT + getHeight()){
      destroy();
    }
  }
  
  // checks for collision with the space station
  public void checkCollisionStation(){
    for (int i = 0; i < game.compartment.length; i++) {
      if (getBounds().intersects(game.compartment[i].getBounds())){
        game.compartment[i].health -= 20;
        destroy();
      }
    }
  }
  
  // die
  public void destroy(){
    for(int i = 0; i < game.bullet.size(); i++){
          if (game.bullet.get(i).equals(this)){
            // create explosion
            game.addExplosion(getHeight(),getX()+getWidth(),getY()+getHeight()/2);
            
            // remove
            game.bullet.remove(i);
            setLocation(-50,-50);
            removeAll();
          }
        }  
  }
}