import java.awt.*;

public abstract class GameObject {
   protected double x, y;
    protected ID id;
    protected double velX, velY;
    public GameObject(double x, double y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setId(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }
    public void setVelX(double velX) {
        this.velX = velX;
    }
    public void setVelY(double velY) {
        this.velY = velY;
    }
    public double getVelX(){
        return velX;
    }

    public double getVelY() {
        return velY;
    }
}
