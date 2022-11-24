import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final Handler handler;
    private final Game game;
    private final double velocity= .001;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;

    }

    public void keyPressed(KeyEvent e) {
        double key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                //key events for player
                if (key == KeyEvent.VK_W) // sets movement for only player 1
                    tempObject.setVelY(velocity * -1);
                if (key == KeyEvent.VK_A) // sets movement for only player 1
                    tempObject.setVelX(velocity * -1);
                if (key == KeyEvent.VK_S) // sets movement for only player 1
                    tempObject.setVelY(velocity);
                if (key == KeyEvent.VK_D) // sets movement for only player 1
                    tempObject.setVelX(velocity);
            }
        }
        if(key == KeyEvent.VK_SPACE) {
            if(game.gameState == Game.STATE.Game) {
                game.gameState = Game.STATE.Shop;
            }
            else if(game.gameState == Game.STATE.Shop) {
                game.gameState = Game.STATE.Game;
            }
        }
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
        double key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                //key events for player
                if (key == KeyEvent.VK_W) // sets movement for only player 1
                    tempObject.setVelY(0);
                if (key == KeyEvent.VK_A) // sets movement for only player 1
                    tempObject.setVelX(0);
                if (key == KeyEvent.VK_S) // sets movement for only player 1
                    tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) // sets movement for only player 1
                    tempObject.setVelX(0);
            }
        }
    }
}
