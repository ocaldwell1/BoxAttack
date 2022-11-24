import java.awt.*;
import java.util.Random;

public class Boss1 extends GameObject{
    Random r = new Random();
    public Boss1(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        velX = .0005;
        velY = .0005;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }
    public void tick() {
        x += velX;
        y += velY;

        if(x <= 0 || x >= Game.WIDTH - 48)
            velX *= -1;
        if(y <= 0 || y >= Game.HEIGHT - 48)
            velY *= -1;
        //handler.addObject(new BasicEnemyTrail(x, y,  ID.BasicEnemyTrail, Color.red, 16, 16,.01, handler));
    }



    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 64, 64);
    }
}
