import javax.swing.*;
import java.awt.*;

public class Window extends Canvas { //class for frame
    private static final long serialVersionUID = 243L ;
    public Window(double width, double height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension((int)width, (int)height));
        frame.setMaximumSize(new Dimension((int)width, (int)height));
        frame.setMinimumSize(new Dimension((int)width, (int)height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows you to close with x button;
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //starts frame in middle;
        frame.add(game);//adds game class to frame
        frame.setVisible(true);//makes it visible
        game.start();
    }
}
