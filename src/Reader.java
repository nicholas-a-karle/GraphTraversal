import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    public static ArrayList<Graph> readFile(File file) throws IOException {
        ArrayList<Graph> graphs = new ArrayList<Graph>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Processing Line: \t" + line);
            graphs.add(readLine(line));
        }
        return graphs;
    }

    public static Graph readSubmission(String line, Graph selGraph) {
        line = line.replaceAll("\\s+", "");
        //return genGraph
        return readLine(line);
    }

    public static Graph readLine(String line) {
        boolean[][] adjacencyMatrix;
        //input cleaning
        //int(int,int)(int,int)(int,int)...
        
        //remove all whitespace
        line = line.replaceAll("\\s+", "");
        System.out.println("Processing Line: \t" + line);

        //read until we hit a parentheses or end

        if (line.indexOf("(") == -1) {
            System.out.println("No Edges");
            //try graph of disconnected nodes
            try {
                adjacencyMatrix = new boolean[Integer.valueOf(line)][Integer.valueOf(line)];
            } catch (Exception e) {
                return new Graph();
            }
            System.out.println("Num Nodes: " + adjacencyMatrix.length);
            return new Graph(adjacencyMatrix);
        } else {
            System.out.println("Edges Should Exist");
            //there are parentheses
            try {
                System.out.println("Start: \"" + line.substring(0, line.indexOf("(")) + "\"");
                adjacencyMatrix = new boolean[Integer.valueOf(line.substring(0, line.indexOf("(")))][Integer.valueOf(line.substring(0, line.indexOf("(")))];
                
            } catch (Exception e) {
                System.out.println("Error");
                return new Graph();
            }
            System.out.println("Num Nodes: " + adjacencyMatrix.length);
            line = line.substring(line.indexOf("(") + 1);
            //line is now int,int)(int,int)...(int,int *)*
            //remove last parentheses if its there
            if (line.charAt(line.length() - 1) == ')') line = line.substring(0, line.length() - 1);

            while (line.length() > 0) {
                int to, from;
                try {
                    to = Integer.valueOf(line.substring(0, line.indexOf(",")));
                    if (line.indexOf(")(") != -1) {
                        from = Integer.valueOf(line.substring(line.indexOf(",") + 1, line.indexOf(")(")));
                        line = line.substring(line.indexOf(")(") + 2);
                    } else {
                        from = Integer.valueOf(line.substring(line.indexOf(",") + 1));
                        line = "";
                    }
                    adjacencyMatrix[to - 1][from - 1] = true;
                    adjacencyMatrix[from - 1][to - 1] = true;
                    System.out.println(to + " to " + from);
                } catch (Exception e) {
                    return new Graph(adjacencyMatrix);
                }
            }
            
        }
        return new Graph(adjacencyMatrix);
    }
    
}
