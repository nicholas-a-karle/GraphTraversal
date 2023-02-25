import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

public class Algorithm {
    
    /* Algorithm: Depth First Search-style Traversal
     * 
     * Given a graph, where there is some ordering of the nodes and/or edges
     * Return the push-pop order of each node
     * 
     * Start at minimum node, push to stack
     * Go to minimum node connected to that node, push to stack
     * Continue, go until a dead end, pop until you find a non-dead end
     * Continue former process, go until nothing is on stack
     * If elements stll remain on the graph, restart at minimum remaining element
     */

    Graph graph;
    boolean[] visited;
    int[] array;
    ArrayList<Integer> allVisits;
    int ptr;

    public Algorithm(Graph graph) {
        this.graph = graph;
    }

    private boolean dead(int node) {
        boolean[] edges = graph.getEdges(node);
        for (int i = 0; i < edges.length; ++i) {
            if (edges[i] && !visited[i]) return false;
        }
        return true;
    }

    private boolean allVisited() {
        for (int i = 0; i < graph.getNumNodes(); ++i) {
            if (!visited[i]) return false;
        }
        return true;
    }

    public void runDFS() {
        array = new int[graph.getNumNodes() * 2 + 1];
        ptr = 0;
        visited = new boolean[graph.getNumNodes()];
        allVisits = new ArrayList<Integer>();

        for (int i = 0; i < graph.getNumNodes(); ++i) {
            if (!visited[i]) {
                System.out.println("Root Call: \tdfs(" + i + ")");
                dfs(i);
                if (!allVisited()) array[ptr++] = -1;
            }
        }
        array[ptr++] = -2;

        
    }

    private void dfs(int node) {
        System.out.println("call: \tdfs(" + node + ")");
        if (!visited[node]) {
            //first visit, push to array
            System.out.println("First Visit on " + node);
            array[ptr++] = node;
            visited[node] = true;
        }
        allVisits.add(node);

        if (!dead(node)) {
            //go to minimum unvisited child
            boolean[] edges = graph.getEdges(node);
            for (int i = 0; i < edges.length; ++i) {
                if (edges[i] && !visited[i]) {
                    System.out.println("calling: \tdfs(" + node + ")");
                    dfs(i);
                }
            }
        } //else { go to previous: let previous calls execute }
        
        //on go to previous:
        //let call die

    }

    public String getTraversal() {
        //-1 indicates a disconnect
        //-2 indicates end
        String str = "{";
        boolean comma = false;
        for (int i = 0; i < array.length; ++i) {
            System.out.println("array[" + i + "] = " + array[i]);
            if (array[i] == -2) {
                break;
            } else if (array[i] == -1) {
                str += "} {";
                comma = false;
            } else if (comma) {
                str += ", " + (array[i] + 1);
            } else {
                str += (array[i] + 1);
                comma = true;
            }
        }
        str += "}";
        return str;
    }

    public int[] getVisitsAndBackstep() {
        int[] all = new int[allVisits.size()];
        for (int i = 0; i < all.length; ++i) {
            all[i] = allVisits.get(i);
        }
        return all;
    }
}
