import java.awt.*;

public class BasicEnemy extends GameObject{
    private Handler handler;
    public BasicEnemy(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = .0001;
        velY = .0001;
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
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
