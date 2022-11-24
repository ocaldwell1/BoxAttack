import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
    Handler handler;
    HUD hud;
    Game game;
    final int[] boxes = {5000, 3500, 9000};

    public Shop(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 48));
        g.drawString("SHOP", (int) (Game.WIDTH / 2 - 100), 50);
        //box 1
        g.setFont(new Font("arial", 0, 12));
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost:" + boxes[0], 110, 140);
        g.drawRect(100, 100, 100, 80);

        //box 2
        g.setFont(new Font("arial", 0, 12));
        g.drawString("Refill Health", 260, 120);
        g.drawString("Cost:" + boxes[1], 260, 140);
        g.drawRect(250, 100, 100, 80);


        //box 3
        g.setFont(new Font("arial", 0, 12));
        g.drawString("Clear Enemies", 410, 120);
        g.drawString("Cost:" + boxes[2], 410, 140);
        g.drawRect(400, 100, 100, 80);

        g.drawString("SCORE:" + hud.getScore(), (int) (Game.WIDTH / 2 - 50)
                , 300);
        g.drawString("Space for game", (int) (Game.WIDTH / 2 - 50)
                , 330);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //box 1
        if (mx >= 100 && mx <= 200
                && game.gameState == Game.STATE.Shop) {
            if (my >= 100 && my <= 180) { //selected box1
                if (hud.getScore() >= boxes[0]) {
                    hud.setScore(hud.getScore() - boxes[0]);
                    boxes[0] += boxes[0];
                    hud.bounds += 20;
                    hud.HEALTH = (100 + (hud.bounds / 2));
                }
            }
        }
        if (mx >= 250 && mx <= 350
                && game.gameState == Game.STATE.Shop) {
            if (my >= 100 && my <= 180) { //selected box2
                if (hud.getScore() >= boxes[1]) {
                    hud.setScore(hud.getScore() - boxes[1]);
                    hud.HEALTH = (100 + (hud.bounds / 2)); //refill health
                    boxes[1] += boxes[1];
                }

            }
        }
        if (mx >= 400 && mx <= 500
                && game.gameState == Game.STATE.Shop) {
            if (my >= 100 && my <= 180) { //selected box3
                if (hud.getScore() >= boxes[2]) {
                    hud.setScore(hud.getScore() - boxes[2]);
                    boxes[2] += boxes[2];
                    handler.clearEnemies();

                }

            }
        }

    }
}