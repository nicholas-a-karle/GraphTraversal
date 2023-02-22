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

    int[][] depthFirstTraversal(Graph graph) {
        boolean[] dead = new boolean[graph.getNumNodes()];
        int[] stack = new int[graph.getNumNodes()];
        int[][] order = new int[graph.getNumNodes()][2];

        

        return order;
    }

}
