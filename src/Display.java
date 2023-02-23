import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem item1a, item1b;
    JPanel controlsPanel;
    DrawPanel drawPanel;
    JLabel label;
    JTextField textInput;
    JButton submitButton;
    JButton clearButton;
    Graph graph;

    public Display(String name, Graph graph) {
        super(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(250, 170);
        setSize(600, 600);

        menuBar = new JMenuBar();
        menu1 = new JMenu("Actions");
        menuBar.add(menu1);
        item1a = new JMenuItem("Open Input File");
        item1b = new JMenuItem("Help");
        menu1.add(item1a);
        menu1.add(item1b);

        controlsPanel = new JPanel();
        drawPanel = new DrawPanel(graph);
        this.graph = graph;
        
        //controlsPanel.setBackground(Color.YELLOW);
        //drawPanel.setBackground(Color.RED);

        drawPanel.setLayout(new BorderLayout());
        label = new JLabel("Enter Data");
        textInput = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        controlsPanel.add(label);
        controlsPanel.add(textInput);
        controlsPanel.add(submitButton);
        controlsPanel.add(clearButton);

        item1a.addActionListener(this);
        item1b.addActionListener(this);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        

        setContentPane(drawPanel);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        getContentPane().add(BorderLayout.SOUTH, controlsPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(item1a)) { //open input file

        } else if (e.getSource().equals(item1b)) { //help
            
        } else if (e.getSource().equals(submitButton)) { //submit
            System.out.println("Submission: " + textInput.getText());
            graph = InputReader.readSubmission(textInput.getText(), graph);
            textInput.setText("");
            drawPanel.updateGraph(graph);
            drawPanel.repaint();
        } else if (e.getSource().equals(clearButton)) { //clear
            graph = new Graph();
            textInput.setText("");
            drawPanel.updateGraph(graph);
            drawPanel.repaint();
        }
    }
}
