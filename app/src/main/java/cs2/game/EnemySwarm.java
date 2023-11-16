package cs2.game;

import java.util.ArrayList;
import java.util.Random;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemySwarm {
  private ArrayList<Enemy> swarm;

  // This constructor should create a swarm of enemies in a grid
  // The grid should be nRows x nCols in size.
  // The enemPic and bullPic should be used to create the Enemy instances
  // that are added to the ArrayList. The enemies should be spaced out
  // in a grid pattern across the top of the screen.
  public Image bp;

  public EnemySwarm(int nRows, int nCols, Image enemPic, Image bullPic) {
    swarm = new ArrayList<Enemy>();
    bp = bullPic;
    for (int x = 0; x < nRows; x++) {
      for (int y = 0; y < nCols; y++) {
        swarm.add(new Enemy(enemPic, bullPic, new Vec2(0 + 175 * y, 0 + 100 * x)));
      }
    }
  }

  // This method should display all enemies in the swarm
  public void display(GraphicsContext g) {
    for (int z = 0; z < swarm.size(); z++) {
      swarm.get(z).display(g);
    }
  }

  // This method should choose one enemy at random from the swarm,
  // and have that enemy shoot a bullet. Return that Bullet.
  public Bullet shoot() {
    Random r = new Random();
    int g = r.nextInt(0,swarm.size());
    Enemy e = swarm.get(g);
    Bullet b = new Bullet(bp, new Vec2(e.pos.getX()+50, e.pos.getY()), new Vec2(0, 20));
    return b; 
  }

}
