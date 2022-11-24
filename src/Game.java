import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public enum STATE {
        Menu,
        Help,
        Gameover,
        Shop,
        Game;
    }
    public STATE gameState = STATE.Menu;
    private static final long serialVersionUID = 240L;
    public static final double WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private final Handler handler;
    private final HUD hud;
    private final Spawn spawner;
    private final Menu menu;
    private final Shop shop;
    // helps keeps player in bounds
    public static double playerBounds(double var, double min, double max) {
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;

    }

    public static void main(String args[]) {
        new Game();
    }

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler, this));
        new Window(WIDTH, HEIGHT, "GAME", this);
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        shop = new Shop(handler, hud, this);
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        spawner = new Spawn(handler, hud);
        Random r = new Random();
        if(gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2-32,HEIGHT / 2 -32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt((int) Game.WIDTH), r.nextInt((int) Game.HEIGHT),
                    ID.BasicEnemy, handler));
        } else { for(int i = 0; i < 30; i++) {
            //spawns menu particles if not in game state
            handler.addObject(new MenuParticle(r.nextInt((int)WIDTH),
                    r.nextInt((int)HEIGHT), ID.MenuParticle, handler));

        }
        }


    }


    public void run() {
        this.requestFocus();
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;
      long timer = System.currentTimeMillis();
      double frames = 0;
      while(running) {
          long now = System.nanoTime();
          delta += (now - lastTime) / ns;
          while (delta >= 1) {
              tick();
              delta--;
          }
          if (running)
              render();
          frames++;
          if (System.currentTimeMillis() - timer > 1000) {
              timer += 1000;
              System.out.println(("FPS: " + frames));
              frames = 0;
          }
      }
      stop();
    }
    private void tick(){
        if( gameState == STATE.Game) { //works but doesnt pause
            handler.tick(); //try only making handler tick in shop state
            hud.tick(this);
            spawner.tick();
            if (HUD.HEALTH <= 0) {
                HUD.HEALTH = 100;
                handler.clearEnemies();
                gameState = STATE.Gameover;
            }
        }
        else if(gameState == STATE.Shop) {
         //   handler.tick(); //try only making handler tick in shop state
            hud.tick(this);
            }

        else if(gameState == STATE.Menu || gameState == STATE.Help) {
            menu.tick();
            handler.tick();

        }
        else if( gameState == STATE.Gameover) {
            menu.tick();
            if (gameState == Game.STATE.Gameover) {
                shop.boxes[0] = 5000;
                shop.boxes[1] = 3500;
                shop.boxes[2] = 9000;
                hud.bounds = 100;
                hud.HEALTH = (100 + (hud.bounds / 2));

            }
            }
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);//creates three buffers
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, (int) WIDTH, (int) HEIGHT);
        if (gameState == STATE.Game) {
            hud.render(g);
            handler.render(g);
        } else if(gameState == STATE.Menu || gameState == STATE.Help
                || gameState == STATE.Gameover) {
            menu.render(g);
            handler.render(g);
         } else if(gameState == STATE.Shop) {
            shop.render(g);
        }

        g.dispose();
        bs.show();
    }


    public synchronized void start() {
        thread = new Thread((this));
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
