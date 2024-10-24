package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
  protected Image img; // the image to be displayed for this sprite
  protected Vec2 pos; // the current position of this sprite


  /* The remained of the constructors and methods should be uncommented
   * as you write your code. I recommend keeping your project in a state
   * that it can always be run, even if it doesn't do much.
   * Then slowly over time, you can un-comment and add in additional
   * features.
   * DO NOT TRY TO WRITE EVERYTHING ALL AT ONCE. IT WILL NOT WORK.
   */


   
  // The constructor should initialize the fields of the class
  public Sprite(Image i, Vec2 p) { 
  img = i;
  pos = p;
  }
  

  
  // This method should draw the image at the current position
  public void display(GraphicsContext g) {
  g.drawImage(img,this.pos.getX(),this.pos.getY());
  }
  
  public boolean intersect(Sprite s){
  return !(
  (this.pos.getX()>(s.pos.getX()+s.img.getWidth()))||
  (this.pos.getY()>(s.pos.getY()+s.img.getHeight()))||
  (this.pos.getX()+this.img.getWidth()<(s.pos.getX()))||
  (this.pos.getY()+this.img.getHeight()<(s.pos.getY())));
  }
  
  // This method should change the location/position of the sprite
  // by the amount specified in the parameter delta
  public void move(Vec2 delta) { 
  this.pos.addThis(delta);
  }
  

}
