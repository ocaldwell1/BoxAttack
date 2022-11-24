import java.awt.*;

public class HUD {
    private Game game;
    public int bounds = 0;
    public static int HEALTH = 100;
    private double score = 0;
    private int level = 1;
    public void tick(Game game) {
        this.game = game;
        if (game.gameState == Game.STATE.Game) {
            HEALTH = (int) Game.playerBounds
                    (HEALTH, 0, 100 + (bounds / 2));// keeps health from going past 0 or past 100
            score += .0001;
        }
    }
    public void render(Graphics g) {
        g.setColor(Color.gray);// color of empty health bar
        g.fillRect(15, 15, 200 + bounds, 32);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200 + bounds, 32);// draws border
        g.drawString("Score: " + score, 15, 64 );
        g.drawString("Level: " + level, 15, 80 );
        g.drawString("Space for Shop/Pause", 15, 96 );
    }
    public void setScore(double score) {
        this.score = score;
    }
    public double getScore() {
        return score;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}
