import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    JButton nextButton;
    JButton prevButton;
    JButton runButton;
    JFileChooser chooser;

    ArrayList<Graph> graphs;
    int graphPtr;

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
        graphs = new ArrayList<Graph>();
        graphs.add(graph);
        graphPtr = 0;
        updateGraph();
        
        //controlsPanel.setBackground(Color.YELLOW);
        //drawPanel.setBackground(Color.RED);

        drawPanel.setLayout(new BorderLayout());
        label = new JLabel("Enter Data");
        textInput = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        runButton = new JButton("Run DFS");
        controlsPanel.add(label);
        controlsPanel.add(textInput);
        controlsPanel.add(submitButton);
        controlsPanel.add(clearButton);
        controlsPanel.add(prevButton);
        controlsPanel.add(nextButton);
        controlsPanel.add(runButton);

        item1a.addActionListener(this);
        item1b.addActionListener(this);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        nextButton.addActionListener(this);
        prevButton.addActionListener(this);
        runButton.addActionListener(this);
        

        setContentPane(drawPanel);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        getContentPane().add(BorderLayout.SOUTH, controlsPanel);
        setVisible(true);
    }

    public void updateGraph() {
        drawPanel.updateGraph(graphs.get(graphPtr));
    }

    public void updateOutputFile() {
        //as per instructions from professor, this shall do some algorithming and file writing
        File file = new File("output.out");
        try {
            if (!file.exists()) file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

            //algorithming
            Algorithm algo;
            String str = "";
            for (int i = 0; i < graphs.size(); ++i) {
                algo = new Algorithm(graphs.get(i));
                algo.runDFS();
                str += "Graph " + (i + 1) + ":\n" + algo.getTraversal() + "\n";
            }
            bw.write(str);
            
                bw.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(item1a)) { //open input file
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                try {
                    graphPtr = 0;
                    graphs = Reader.readFile(chooser.getSelectedFile());
                    drawPanel.updateGraph(graphs.get(graphPtr));
                    drawPanel.repaint();
                    updateOutputFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource().equals(item1b)) { //help
            
        } else if (e.getSource().equals(submitButton)) { //submit new graph or alteration to current
            String text = textInput.getText();
            textInput.setText("");

            Graph newGraph = Reader.readSubmission(text, graphs.get(graphPtr));

            if (text.charAt(0) == 'a') {
                graphs.set(graphPtr, newGraph);
            } else {
                graphs.add(newGraph);
                graphPtr = graphs.size() - 1;
                drawPanel.updateGraph(graphs.get(graphPtr));
                drawPanel.repaint();
            }

            updateOutputFile();
            
        } else if (e.getSource().equals(clearButton)) { //clear
            graphPtr = 0;
            graphs = new ArrayList<Graph>();
            graphs.add(new Graph());
            drawPanel.updateGraph(graphs.get(graphPtr));
            drawPanel.repaint();

            updateOutputFile();

        } else if (e.getSource().equals(prevButton)) { //previous graph
            if (graphPtr > 0) --graphPtr;
            drawPanel.updateGraph(graphs.get(graphPtr));
            drawPanel.repaint();

        } else if (e.getSource().equals(nextButton)) { //next graph
            if (graphPtr < graphs.size() - 1) ++graphPtr;
            drawPanel.updateGraph(graphs.get(graphPtr));
            drawPanel.repaint();

        } else if (e.getSource().equals(runButton)) { //run search
            System.out.println("Running Depth First Search");
            Algorithm algo = new Algorithm(graphs.get(graphPtr));
            algo.runDFS();
            System.out.println(algo.getTraversal());
            drawPanel.traversal = algo.getVisitsAndBackstep();
        }   
    }
}
