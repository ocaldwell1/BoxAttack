import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 20);
        g.setFont(fnt);
        g.setColor(Color.white);
        if (game.gameState == Game.STATE.Menu) {
            g.drawString("Box Attack", 210, 50);
            g.drawRect(220, 100, 220, 64);
            g.drawString("Play", 230, 150);
            g.drawRect(220, 200, 220, 64);
            g.drawString("Help", 230, 250);
            g.drawRect(220, 300, 220, 64);
            g.drawString("Quit", 230, 350);
        } else if (game.gameState == Game.STATE.Help) {
            g.drawString("Help", 210, 50);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Use wasd keys for movement and dodge other " +
                    "boxes", 90, 100);
            g.drawRect(220, 300, 220, 64);
            g.drawString("Back", 230, 350);
        } else if (game.gameState == Game.STATE.Gameover) {
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 210, 100);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Score: " +hud.getScore() , 210, 200);
            g.drawString("Level: " + hud.getLevel(), 210, 250);
            g.setFont(fnt2);
            g.drawRect(220, 300, 220, 64);
            g.drawString("Try Again", 240, 350);


        }
    }

    public void tick() {

    }
    public void mousePressed(MouseEvent e) {
        // stores our x and y pos on screen in variable and checks if mouse
        // is over specified dimensions
        double mx = e.getX();
        double my = e.getY();
        if(game.gameState == Game.STATE.Menu) {
        // Play button
        if(mouseOver(mx, my, 220, 100, 220, 64)) {
            game.gameState = Game.STATE.Game;
            handler.addObject(new Player(Game.WIDTH / 2-32,
                    Game.HEIGHT / 2 -32, ID.Player, handler));
            handler.clearEnemies();
            handler.addObject(new BasicEnemy(r.nextInt((int)
                    Game.WIDTH), r.nextInt((int) Game.HEIGHT),
                    ID.BasicEnemy, handler));
        }
        // Quit button
        if(mouseOver(mx, my, 220, 300, 220,64)) {
            System.exit(0);
        }// Help Button
        if (mouseOver(mx, my, 220, 200, 220, 64)) {
            game.gameState = Game.STATE.Help;
        }
        }
        if (mouseOver(mx, my, 220, 300, 220,
                64) && game.gameState == Game.STATE.Help) {
        game.gameState = Game.STATE.Menu;
         return;
        }
        if(mouseOver(mx, my, 220, 300, 220, 64)
                && game.gameState == Game.STATE.Gameover) {
            hud.setScore(0);
            hud.setLevel(1);
            game.gameState = Game.STATE.Menu;
        }
    }
    public void mouseReleased(MouseEvent e) {

    }
    private boolean mouseOver(double mx, double my, double x,
                              double y, double width, double height) {
        //checks to see position of mouse
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }
}
