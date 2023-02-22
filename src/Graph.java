
public class Graph {

    private boolean[][] matrix;
    private int numNodes;
    private boolean validMatrix;

    /**
     * Default constructor, defines a graph of 1 node with no connections
     */
    public Graph() {
        matrix = new boolean[][] {{false}};
        numNodes = 1;
        validMatrix = checkValidity();
        extendMatrix(numNodes);
    }

    /**
     * Public constructor with adjacency matrix parameter
     * @param adjacencyMatrix
     */
    public Graph(boolean[][] adjacencyMatrix) {
        matrix = adjacencyMatrix;
        numNodes = adjacencyMatrix.length;
        validMatrix = checkValidity();
        extendMatrix(numNodes);
    }

    /**
     * Accessor method to return copy of matrix
     * @return Copy of graph's adjacency matrix
     */
    public boolean[][] getMatrix() {
        return matrix;
    }

    /**
     * Accessor method to the edge of two nodes
     * @param i Index of from node
     * @param k Index of to node
     * @return Whether or not there is an edge from i to k
     */
    public boolean getEdge(int i, int k) {
        return matrix[i][k];
    }

    /**
     * Accessor method to the edges from a node
     * @param i Index of node
     * @return List of edges
     */
    public boolean[] getEdges(int i) {
        return matrix[i];
    }

    /**
     * Accessor method to return number of nodes in graph
     * @return Number of nodes in the graph
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Check validity function that uses graph's matrix
     * @return Whether the graph has a valid adjacency matrix (n x n, where n != 0)
     */
    public boolean checkValidity() {
        return checkValidity(matrix);
    }

    /**
     * Check validity function that uses parameter matrix
     * @param adjacencyMatrix boolean 2d-array defining an adjacency matrix
     * @return Whether the adjacency matrix is valid (n x n, where n != 0)
     */
    public boolean checkValidity(boolean[][] adjacencyMatrix) {
        if (adjacencyMatrix.length == 0) return false;
        for (int i = 0; i < adjacencyMatrix.length; ++i) {
            if (adjacencyMatrix[i].length != adjacencyMatrix.length) return false;
        }
        return true;
    }

    /**
     * A function to make the matrix symmetric so we can optimize some functions, aka matrix[i][k] = matrix[k][i]
     * @return Whether the operation was completed successfully
     */
    public boolean symmetrizeMatrix() {
        if (validMatrix) {

            for (int i = 0; i < numNodes; ++i) {
                for (int k = 0; k < numNodes; ++k) {
                    if (matrix[i][k] != matrix[k][i]) {
                        if (matrix[i][k] || matrix[k][i]) {
                            matrix[i][k] = true;
                            matrix[k][i] = true;
                        }
                    }
                }
            }
            return true;

        } else {
            return false;
        }
    }
    
    /**
     * A private function to extend the matrix when additional nodes are required, does not modify represented graph
     * @param n How many nodes to extend the matrix by
     * @return Whether the operation was completed successfully
     */
    private boolean extendMatrix(int n) {
        boolean[][] temp = new boolean[matrix.length + n][matrix.length + n];
        for (int i = 0; i < numNodes; ++i) {
            for (int k = 0; k < numNodes; ++k) {
                temp[i][k] = matrix[i][k];
            }
        }
        matrix = temp;
        return true;
    }

    /**
     * A function to add disconnected nodes to the graph
     * @param n Number of nodes to add
     * @return Whether the operation was completed successfully
     */
    public boolean addNodes(int n) {
        numNodes += n;
        if (numNodes > matrix.length) {
            return extendMatrix(matrix.length / 2 + n);
        }
        return true;
    }

    /**
     * A function to add an edge to the graph
     * @param a Node A connects to Node B, value of index of node
     * @param b Node B connects to Node A, value of index of node
     * @return Whether the operation was completed successfully
     */
    public boolean addEdge(int a, int b) {
        if (a < numNodes && b < numNodes) {
            matrix[a][b] = true;
            matrix[b][a] = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * A function to add multiple edges to the graph
     * @param edges A collection of pairs of node indices
     * @return Whether the operation was completed successfully
     */
    public boolean addEdges(int[][] edges) {
        for (int i = 0; i < edges.length; ++i) {
            if (edges[i].length < 2) return false;
            if (edges[i][0] >= numNodes) return false;
            if (edges[i][1] >= numNodes) return false;
        }
        for (int i = 0; i < edges.length; ++i) {
            matrix[edges[i][0]][edges[i][1]] = true;
            matrix[edges[i][1]][edges[i][0]] = true;
        }
        return true;
    }

    /**
     * A function to append a graph to this graph
     * @param newMatrix The adjacency matrix of the other graph
     * @return If the operation was completed successfully
     */
    public boolean mergeGraph(boolean[][] newMatrix) {
        int startNode = numNodes;
        addNodes(newMatrix.length);
        for (int i = 0; i < newMatrix.length; ++i) {
            for (int k = 0; k < newMatrix[i].length; ++k) {
                matrix[i + startNode][k + startNode] = newMatrix[i][k];
            }
        }
        return true;
    }

    /**
     * A function to replace this graph with another graph
     * @param newMatrix The adjacency matrix of the other graph
     * @return If the operation was completed successfully
     */
    public boolean setGraph(boolean [][] newMatrix) {
        if (newMatrix.length > matrix.length) {
            addNodes(newMatrix.length - matrix.length / 2);
        }
        for (int i = 0; i < newMatrix.length; ++i) {
            for (int k = 0; k < newMatrix.length; ++k) {
                matrix[i][k] = newMatrix[i][k];
            }
        }
        return true;
    }
}
