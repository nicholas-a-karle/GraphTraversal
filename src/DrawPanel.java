import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    int numNodes;
    boolean[][] adjacency;
    int width;
    int height;
    
    public DrawPanel() {
        super();
        width = getWidth();
        height = getHeight();
    }

    public void paintComponent(Graphics g) {
        //draw nodes in a circle
        //draw lines between circles
        //lines connect to a small circle which is exactly a node's radius smaller in radius than the bigger circle
        


    }

}
