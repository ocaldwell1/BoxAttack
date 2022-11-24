import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private double scorekeep = 0;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    void tick() {
        scorekeep += .0001;
        if (scorekeep >= 450) {
            scorekeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (hud.getLevel() % 3 == 0) {
                handler.addObject(new BasicEnemy(r.nextInt((int) Game.WIDTH), r.nextInt((int) Game.HEIGHT),
                        ID.BasicEnemy, handler));
            }
            if (hud.getLevel() % 5 == 0) {
                handler.addObject(new FastEnemy(r.nextInt((int) Game.WIDTH), r.nextInt((int) Game.HEIGHT),
                        ID.BasicEnemy, handler));
            }
            if (hud.getLevel() % 12 == 0) {
                handler.addObject(new SmartEnemy(r.nextInt((int) Game.WIDTH), r.nextInt((int) Game.HEIGHT),
                        ID.BasicEnemy, handler));
            }
            if (hud.getLevel() % 18 == 0) {
                handler.addObject(new Boss1(r.nextInt((int) Game.WIDTH),
                        r.nextInt((int) Game.HEIGHT), ID.Boss1,
                        handler));
            }
            if (hud.getLevel() % 17 == 0) {
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt((int) Game.WIDTH),
                        r.nextInt((int) Game.HEIGHT), ID.BasicEnemy,
                        handler));
                ;
                handler.addObject(new FastEnemy(r.nextInt((int) Game.WIDTH),
                        r.nextInt((int) Game.HEIGHT), ID.FastEnemy,
                        handler));
                handler.addObject(new SmartEnemy(r.nextInt((int) Game.WIDTH),
                        r.nextInt((int) Game.HEIGHT), ID.SmartEnemy,
                        handler));

            }
        }
    }
}
