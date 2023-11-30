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
import java.util.*;

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
    Set<KeyCode> keys = new HashSet<KeyCode>();
    Image i = new Image("file:bullet-png-pictures-1.png");
    Image j = new Image("file:f-22_7.png.pc-adaptive.full.medium.png");
    Image k = new Image(
        "file:cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA5L2xhdXJhc3RlZmFubzI2Nl9waG90b19vZl9hX3ZpbnRhZ2VfdWZvX3JlYWxpc3RpY19pc29sYXRlZF9vbl93aF8yYWRkMjNmNS1jZDA0LTQyNTItODdkMy1iNjY1ODQwNTdiYjZfMS5wbmc.png");
    Player p = new Player(j, i, new Vec2(400, 700));
    p.display(g);
    ArrayList<Bullet> b = new ArrayList<Bullet>();
    ArrayList<Bullet> b2 = new ArrayList<Bullet>();
    canvas.requestFocus();
    canvas.setOnKeyPressed((KeyEvent a) -> {
      keys.add(a.getCode());
    });
    canvas.setOnKeyReleased((KeyEvent a) -> {
      keys.remove(a.getCode());
    });

    AnimationTimer timer = new AnimationTimer() {
      int w = 0;
      int lives = 5;
      int score = 0;
      EnemySwarm es = new EnemySwarm(5, 5, k, i);

      public void handle(long t) {
        if (lives != 0) {
          if (keys.contains(KeyCode.UP) && p.pos.getY() > 0 && !keys.contains(KeyCode.W))
            p.moveUp();
          if (keys.contains(KeyCode.DOWN) && p.pos.getY() < 750 && !keys.contains(KeyCode.S))
            p.moveDown();
          if (keys.contains(KeyCode.LEFT) && p.pos.getX() > 0 && !keys.contains(KeyCode.A))
            p.moveLeft();
          if (keys.contains(KeyCode.RIGHT) && p.pos.getX() < 764 && !keys.contains(KeyCode.D))
            p.moveRight();
          if (keys.contains(KeyCode.W) && p.pos.getY() > 0 && !keys.contains(KeyCode.UP))
            p.moveUp();
          if (keys.contains(KeyCode.S) && p.pos.getY() < 750 && !keys.contains(KeyCode.DOWN))
            p.moveDown();
          if (keys.contains(KeyCode.A) && p.pos.getX() > 0 && !keys.contains(KeyCode.LEFT))
            p.moveLeft();
          if (keys.contains(KeyCode.D) && p.pos.getX() < 764 && !keys.contains(KeyCode.RIGHT))
            p.moveRight();
          if (keys.contains(KeyCode.SPACE) && (w % 10 == 0))
            b2.add(p.shoot());
          w++;
          for (int d = 0; d < b.size(); d++) {
            if (b.get(d).intersect(p)) {
              b.remove(d);
              System.out.println("i");
              lives--;
            }
            for (int m = 0; m < b2.size(); m++) {
              if (b.get(d).intersect(b2.get(m)))
                b.remove(d);
            }
          }
          if (es.swarm.size() == 0) {
            es = new EnemySwarm(5, 5, k, i);
          }
          for (int f = 0; f < b2.size(); f++) {
            if (es.intersect(b2.get(f))) {
              b2.remove(f);
              score++;
            }
          }
          if (es.intersect(p)) {
            p.pos = new Vec2(400, 700);
            p.display(g);
            lives--;
          }
          if (w % 10 == 0)
            b.add(es.shoot());
          g.setFill(Color.BLACK);
          g.fillRect(0, 0, 800, 800);
          p.display(g);
          es.display(g);
          for (int x = 0; x < b.size(); x++) {
            b.get(x).display(g);
            b.get(x).update();
          }
          for (int k = 0; k < b2.size(); k++) {
            b2.get(k).display(g);
            b2.get(k).update();
          }
          g.setFill(Color.WHITE);
          g.fillText("Score: " + score, 700, 10);
          g.fillText("HP: " + lives, 100, 10);
        }
        if (lives < 1) {
            g.setFill(Color.RED);
            g.fillRect(0, 0, 800, 800);
            g.setFill(Color.BLACK);
            g.fillText("You are lose", 350, 300);
            g.fillText("Press ENTER to play again", 350, 325);
            g.fillText("Score: " + score, 700, 10);
          g.fillText("HP: " + lives, 100, 10);
            if (keys.contains(KeyCode.ENTER))
              lives += 5;
          }
      }
    };
    timer.start();
  };

}
