public class InputReader {
    
    public static void readFile() {

    }

    public static Graph readSubmission(String text, Graph graph) {
        //takes input from submission bar at bottom of window
        /* Types of Input:
         * Add Node:
         * Empty:
         * "addnode()"
         * With edges to
         * "add(1, ..., n-1)"
         * if numbers not already in graph, add them 
         * 
         * Add Edge:
         * "addedge"
         * 
         * Remove Node:
         * "removenode(node)"
         * ignore if not in graph
         * 
         * Remove Edge:
         * "removeedge(to, from)"
         * ignore if not in graph
         * 
         * Equivalent function names                        code(also usable)
         * addnode      an  n                               0   00
         * addedge      ae  e                               1   01   
         * removenode   rn  deletenode  delnode remnode dn  2   10
         * removeedge   re  deleteedge  deledge remedge de  3   11
         * null                                             -1  --
         * 
        */
        Graph newGraph = graph;

        String[] code0Strings = new String[] {"addnode", "an", "n", "0", "00"};
        String[] code1Strings = new String[] {"addedge", "ae", "e", "1", "01"};
        String[] code2Strings = new String[] {"removenode", "rn", "deletenode", "delnode", "remnode", "dn", "2", "10"};
        String[] code3Strings = new String[] {"removeedge", "re", "deleteedge", "deledge", "remedge", "de", "3", "11"};

        int code = -1;
        text = text.replaceAll(" ", "");
        String function;
        String parameters;
        if (text.indexOf("(") == -1) {
            function = text;
            parameters = "";
        } else {
            function = text.substring(0, text.indexOf("("));
            parameters = text.substring(text.indexOf("(") + 1);
        }

        if (parameters.length() > 0 && parameters.charAt(parameters.length() - 1) == ')') {
            parameters = parameters.substring(0, parameters.length() - 1);
        }

        System.out.println("Text: \t\t" + text);
        System.out.println("Function Name: \t" + function);
        System.out.println("Parameters: \t" + parameters);

        for (String str : code0Strings) {
            if (function.equals(str)) {
                code = 0;
                break;
            }
        }
        if (code == -1) {
            for (String str : code1Strings) {
                if (function.equals(str)) {
                    code = 1;
                    break;
                }
            }
            if (code == -1) {
                for (String str : code2Strings) {
                    if (function.equals(str)) {
                        code = 2;
                        break;
                    }
                }
                if (code == -1) {
                    for (String str : code3Strings) {
                        if (function.equals(str)) {
                            code = 3;
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("Code: \t\t" + code);

        //get parameters as integers
        int numParams = (parameters.length() > 0) ? 1 : 0;
        for (char c : parameters.toCharArray()) {
            if (c == ',') ++numParams;
        }
        int[] params = new int[numParams];
        System.out.println("Expected Number of Parameters: " + numParams);
        int i = 0;
        while (parameters.length() > 0) {
            try {
                if (parameters.indexOf(",") != -1) {
                    System.out.println("params [" + i + "] = Integer value of \"" + parameters.substring(0, parameters.indexOf(",")) + "\"");
                    params[i] = Integer.valueOf(parameters.substring(0, parameters.indexOf(",")));
                    parameters = parameters.substring(parameters.indexOf(",") + 1);
                    ++i;
                } else {
                    params[i] = Integer.valueOf(parameters);
                    break;
                }
            } catch (Exception e) {
                System.out.println("ERROR");
                return newGraph;
            }
        }

        System.out.print("Parameters: ");
        for (int p : params) {
            System.out.print(p + "  ");
        }
        System.out.println();

        switch (code) {
            case -1:
                break;
            case 0: //add node
            System.out.println("Code 0: Add Node(s)");
                for (i = 0; i < params.length; ++i) {
                    newGraph.addNodes(params[i]);
                }
                if (params.length == 0) newGraph.addNodes(1);
                break;
            case 1: //add edge
            System.out.println("Code 1: Add Edge(s)");
                for (i = 0; i < params.length - 1; i+=2) {
                    newGraph.addEdge(params[i] - 1, params[i+1] - 1);
                }
                break;
            case 2: //remove node
                System.out.println("Code 2: Remove Node(s)");
                for (i = 0; i < params.length; ++i) {
                    newGraph.removeNode(params[i] - 1);   
                }
                break;
            case 3: //remove edge
                System.out.println("Code 3: Remove Edge(s)");
                for (i = 0; i < params.length - 1; i+=2) {
                    newGraph.removeEdge(params[i] - 1, params[i+1] - 1);
                }
                break;
        }
        return newGraph;
    }

}

