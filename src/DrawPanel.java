import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    Graph graph;
    
    public DrawPanel(Graph graph) {
        super();
        this.graph = graph;
    }

    public void updateGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Draws a circle centered at (x, y) with radius r
     * @param g Graphics object to draw with
     * @param x x component of center
     * @param y y component of center
     * @param r radius of circle
     */
    public void drawCircle(Graphics g, int x, int y, int r) {
        int distr = (int) Math.sqrt(2 * r * r);
        g.fillOval(x - distr, y - distr, 2 * distr, 2 * distr);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        int minDim = Math.min(getWidth(), getHeight());
        int drawCenterX = (int) (0.45 * getWidth());
        int drawCenterY = (int) (0.45 * getHeight());
        int nodeCircRadius = (int) (0.25 * minDim);
        int nodeRadius = (int) (0.0125 * minDim);
        int labelCircRadius = (int) (0.3 * minDim);

        double theta = 2 * Math.PI / graph.getNumNodes();

        for (int i = 0; i < graph.getNumNodes(); ++i) {
            int cx = (int) (drawCenterX + nodeCircRadius * Math.cos(theta * i));
            int cy = (int) (drawCenterY + nodeCircRadius * Math.sin(theta * i));
            drawCircle(g, cx, cy, nodeRadius);

            cx = (int) (drawCenterX + labelCircRadius * Math.cos(theta * i));
            cy = (int) (drawCenterY + labelCircRadius * Math.sin(theta * i));
            g.drawString(graph.getLabel(i), cx, cy);

            for (int k = 0; k < graph.getNumNodes(); ++k) {
                if (graph.getEdge(i, k)) {
                    cx = (int) (drawCenterX + nodeCircRadius * Math.cos(theta * i));
                    cy = (int) (drawCenterY + nodeCircRadius * Math.sin(theta * i));
                    int nx = (int) (drawCenterX + nodeCircRadius * Math.cos(theta * k));
                    int ny = (int) (drawCenterY + nodeCircRadius * Math.sin(theta * k));
                    g.drawLine(cx, cy, nx, ny);
                }
            }
        }



    }

}
