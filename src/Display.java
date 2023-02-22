import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem item1a, item1b;
    JPanel controlsPanel;
    JPanel drawPanel;
    JLabel label;
    JTextField textInput;
    JButton submitButton;
    JButton clearButton;

    public Display(String name) {
        super(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(250, 170);
        setSize(700, 500);

        menuBar = new JMenuBar();
        menu1 = new JMenu("Actions");
        menuBar.add(menu1);
        item1a = new JMenuItem("Open Input File");
        item1b = new JMenuItem("Help");
        menu1.add(item1a);
        menu1.add(item1b);

        controlsPanel = new JPanel();
        drawPanel = new DrawPanel();
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
            
        } else if (e.getSource().equals(clearButton)) { //clear
            
        }
    }
}
