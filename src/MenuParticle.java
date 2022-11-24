import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{
    Random r = new Random();
    private final Color color;
    int direction = 0;
    public MenuParticle(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        direction = r.nextInt(2);
        if(direction == 0) {
            velX = .02;
            velY = .09;
        }
            else {
                velY = .02;
                velX = .09;
            }
        velX = .0003;
        velY = .0005;
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick() {
        x += velX;
        y += velY;
        if(y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1; // keeps enemy going back and forth instead of off screen
        if(x <= 0 || x >= Game.WIDTH - 32)
            velX *= -1;
        //handler.addObject(new BasicEnemyTrail(x, y,  ID.BasicEnemyTrail, Color.red, 16, 16,.01, handler));
    }



    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
