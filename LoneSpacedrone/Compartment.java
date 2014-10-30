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
    this.type = type;
    this.spaceship = spaceship;
    this.game = game;
    
    // health
    maxHealth = 500;
    health = maxHealth;
    
    switch(type) {
      // observatory
      case OBSERVE:
        sprite = new GImage("observatory.png");
        add(sprite);
        initHealth();
        break;
        
        // connector
      case CONNECT:
        // draw image
        sprite = new GImage("connection.png");
        add(sprite);
        initHealth();
        break;
        
      case SHIELD:
        // draw image
        sprite = new GImage("shield.png");
        add(sprite);
        initHealth();
        break;
    }
    
    // add powerup images
    resourceImage = new GImage("resourceIcon.png");
    resourceImage.setVisible(false);
    add(resourceImage,-resourceImage.getWidth(),sprite.getHeight()/2-resourceImage.getHeight()/2);
    
    healthImage = new GImage("healthIcon.png");
    healthImage.setVisible(false);
    add(healthImage,-healthImage.getWidth(),sprite.getHeight()/2-healthImage.getHeight()/2);
  }
  
  // oneTimeStep
  public void oneTimeStep(){
    if (alive) {
      checkCollisionLazer();
      updateHealth();
      checkHealth();
      
      if (type == Type.OBSERVE) {
        spawnResource();
        
        if (healthImage.isVisible() || resourceImage.isVisible()){
          resourceClock();
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
      GRectangle lineBound = spaceship.leftLine.getBounds();
      lineBound.translate(spaceship.getX(),spaceship.getY());
      
      if (getBounds().intersects(lineBound)){
        repair();
      }
    }
    
    // right
    if (game.spaceship.rightLine.isVisible() == true) {
      // find rectangle
      GRectangle lineBound = spaceship.rightLine.getBounds();
      lineBound.translate(spaceship.getX(),spaceship.getY());
      
      if (getBounds().intersects(lineBound)){
        repair();
      }
    }
    
    // up
    if (game.spaceship.upLine.isVisible() == true) {
      // find rectangle
      GRectangle lineBound = spaceship.upLine.getBounds();
      lineBound.translate(spaceship.getX(),spaceship.getY());
      
      if (getBounds().intersects(lineBound)){
        repair();
      }
    }
    
    // down
    if (game.spaceship.downLine.isVisible() == true) {
      // find rectangle
      GRectangle lineBound = spaceship.downLine.getBounds();
      lineBound.translate(spaceship.getX(),spaceship.getY());
      
      if (getBounds().intersects(lineBound)){
        repair();
      }
    }
  }
  
  private void drawBoxes() {
    backRect = new GRect(64,64);
    backRect.setFilled(true);
    backRect.setFillColor(Color.BLACK);
    add(backRect);
    
    fillRect = new GRect(0,64);
    fillRect.setFilled(true);
    fillRect.setFillColor(Color.BLUE);
    add(fillRect);
  }
  // draw health bar
  private void initHealth(){
    // draw the red part of the health bar
    healthFill = new GRect(sprite.getWidth()+4,0,5,sprite.getHeight());
    healthFill.setFilled(true);
    healthFill.setColor(Color.BLUE);
    add(healthFill);
    
    // draw the outline of the health bar
    healthFrame = new GRect(sprite.getWidth()+4,0,5,sprite.getHeight());
    healthFrame.setColor(Color.MAGENTA);
    add(healthFrame);
  }
  
  // update health bar upon hit
  public void updateHealth(){
    healthFill.setSize(5,(health*sprite.getHeight())/(maxHealth));
  }
  
  // dies if health is too low
  private void checkHealth(){
    if (health <= 0) {
      alive = false;
      setVisible(false);
      setLocation(-500,game.rand.nextInt((int).75*game.APPLICATION_HEIGHT)+game.APPLICATION_HEIGHT/8);  
    }
  }
  
  // repair the station and collect boosts
  private void repair(){
    // repair
    if (game.resources >= 3 && health < maxHealth) {
      game.resources -= 6;
      health++;
    }
    
    // collect boosts
    if (type == Type.OBSERVE && (resourceImage.isVisible() || healthImage.isVisible())){
      if (resourceImage.isVisible()){
        resourceImage.setVisible(false);
        game.resources += 1000;
      } else {
        healthImage.setVisible(false);
        game.playerHealth = game.maxPlayerHealth;
      }
    }
  }
  
  // add collectable resources  
  private void spawnResource(){
    if (!resourceImage.isVisible() & !healthImage.isVisible()){
      if (game.rand.nextInt(3000) == 0) {
        if (game.rand.nextInt(2) == 0){
          healthImage.setVisible(true);
        } else {
          resourceImage.setVisible(true);
        }
      }
    }
  }
  
  // resource clock
  public void resourceClock(){
    if (resourceClockCount > 0) {
      // count down
      resourceClockCount--;
    } else {
      // hide images
      resourceImage.setVisible(false);
      healthImage.setVisible(false);
      
      // reset clock
      resourceClockCount = resourceClockMax;
    }
  }
}