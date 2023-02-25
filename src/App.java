public class App {
    public static void main(String[] args) throws Exception {
        boolean[][] adjacencyMatrix = {
            {false, true, true},
            {false, false, true},
            {false, false, false}
        };
        Graph graph = new Graph();
        Display d = new Display("Graph Traversal", graph);
    }
}
