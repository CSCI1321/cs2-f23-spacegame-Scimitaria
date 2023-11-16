package cs2.game;

import cs2.util.Vec2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class SpaceGameApp extends Application {
  public void start(Stage stage) {
    stage.setTitle("My roflcopter goes SOI SOI SOI");
    stage.show();
    // You can change the window size here if you want
    Canvas canvas = new Canvas(800, 800);
    stage.setScene(new Scene(new StackPane(canvas)));
    GraphicsContext g = canvas.getGraphicsContext2D();

    /*
     * Your main game logic will go here
     * This will likely mean creating instances of Player and EnemySwarm, along
     * with a collection (probably ArrayList) of Bullets.
     * 
     * You should create a KeyPressed event handler that moves the player
     * when the left or right arrow keys are pressed, and fires a bullet when
     * the space bar is pressed. Add the bullet created to the collection.
     * 
     * You should also create an AnimationTimer that displays everything and
     * moves the bullets around the screen. Also, an enemy chosen from the swarm
     * at random should shoot and have that bullet added to the collection.
     */
    
    Image i = new Image("file:bullet-png-pictures-1.png");
    Image j = new Image("file:f-22_7.png.pc-adaptive.full.medium.png");
    Image k = new Image(
        "file:cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA5L2xhdXJhc3RlZmFubzI2Nl9waG90b19vZl9hX3ZpbnRhZ2VfdWZvX3JlYWxpc3RpY19pc29sYXRlZF9vbl93aF8yYWRkMjNmNS1jZDA0LTQyNTItODdkMy1iNjY1ODQwNTdiYjZfMS5wbmc.png");
    Player p = new Player(j, i, new Vec2(400, 700));
    p.display(g);
    EnemySwarm es = new EnemySwarm(5, 5, k, i);
    ArrayList<Bullet> b = new ArrayList<Bullet>();
    canvas.requestFocus();
    canvas.setOnKeyPressed((KeyEvent a) -> {
      if (a.getCode().equals(KeyCode.LEFT)&&(p.pos.getX()>0)) {
        p.moveLeft();
      }
      if (a.getCode().equals(KeyCode.RIGHT)&&(p.pos.getX()<770)) {
        p.moveRight();
      }
      if (a.getCode().equals(KeyCode.SPACE)) {
        b.add(p.shoot());
      }
    });
    
    AnimationTimer timer = new AnimationTimer() {
      int w = 0; 
      public void handle(long t) {
        w++;
        if(w%5==0) b.add(es.shoot());
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 800, 800);
        p.display(g);
        es.display(g);
        for (int x = 0; x < b.size(); x++) {
          b.get(x).display(g);
          b.get(x).update();
        }
      }
    };
    timer.start();
  };

}
