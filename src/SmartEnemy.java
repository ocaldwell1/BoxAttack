import java.awt.*;

public class SmartEnemy extends GameObject{
    private Handler handler;
    private GameObject player;
    public SmartEnemy(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player)
                player = handler.object.get(i);
        }
        //velX = .0001;
        //velY = .0001;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick() {
        x += velX;
        y += velY;
        double diffxminus = x - player.getX() - 1;
        double diffyminus = x - player.getY() - 1;
        double diffx = x- player.getX();
        double diffy = y - player.getY();
        double distance = Math.sqrt((diffx * diffx) + (diffy * diffy));
        velX = (-1 / distance * diffxminus) * .0001;
        velY = (-1 / distance * diffyminus) * .0001;
        if(y <= 0 || y >= Game.HEIGHT - 32)
             velY *= -1; // keeps enemy going back and forth instead of off screen
        if(x <= 0 || x >= Game.WIDTH - 32)
            velX *= -1;
        //handler.addObject(new BasicEnemyTrail(x, y,  ID.BasicEnemyTrail, Color.red, 16, 16,.01, handler));
    }



    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}

