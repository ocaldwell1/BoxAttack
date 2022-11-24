import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    Random r = new Random();
    int collisionscore = 0;
    Handler handler;
    public Player(double x, double y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        // may need linux to replace doubles with double to slow down speed of box
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.playerBounds(x, 0, Game.WIDTH - 32);// keeps player in bound
        y = Game.playerBounds(y, 0, Game.HEIGHT - 65);
        collision();
    }
        private void collision() {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy ||
                        tempObject.getId() == ID.SmartEnemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        collisionscore++;
                        if (collisionscore >= 10000) {
                            collisionscore = 0;
                            HUD.HEALTH -= .000001;
                        }
                    }
                }
                if (tempObject.getId() == ID.Boss1) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        collisionscore++;
                        if (collisionscore >= 10000) {
                            collisionscore = 0;
                            HUD.HEALTH -= 00001;
                        }
                    }
                }
            }
        }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x,(int)y,32,32);
    }
}
