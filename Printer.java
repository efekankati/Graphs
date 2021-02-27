import java.util.ArrayList;

public class Printer {
    /*This function traverses the adjacency list and prints graph accordingly*/
    static void printGraphStructure(ArrayList<ArrayList<Vertex>> graph) {
        System.out.println("Graph structure:");
        int j;
        for (ArrayList<Vertex> vertices : graph) {
            System.out.print(vertices.get(0).name + "(" + vertices.get(0).weight + ")-->");
            for (j = 1; j < vertices.size() - 1; j++) {
                System.out.print(vertices.get(j).name + " ");
            }
            System.out.println(vertices.get(j).name);
        }
    }

    /*This function compares the child's capacity and changes the parent's capacity and message
    if the child's weight is larger*/
    public static void message_passing_print() {
        System.out.println("Message passing:");
        for (ArrayList<Vertex> vertices : Graph.broadcast_graph) {
            if (vertices.get(1).weight > vertices.get(0).weight) {
                if (vertices.get(0).message == null || vertices.get(1).message == null) {
                    if (vertices.get(0).message == null) {
                        vertices.get(0).message = "[" + vertices.get(1).name + "," + vertices.get(1).weight + "]";
                        Graph.changer(vertices.get(0).name, vertices.get(0).message, vertices.get(1).weight);
                    }
                    if (vertices.get(1).message == null) {
                        vertices.get(1).message = "[" + vertices.get(1).name + "," + vertices.get(1).weight + "]";
                        Graph.changer(vertices.get(1).name, vertices.get(1).message, vertices.get(1).weight);
                    }
                } else {
                    vertices.get(0).message = vertices.get(1).message;
                    Graph.changer(vertices.get(0).name, vertices.get(0).message, vertices.get(0).weight);
                    vertices.get(1).message = vertices.get(1).message;
                    Graph.changer(vertices.get(1).name, vertices.get(1).message, vertices.get(1).weight);
                }
                System.out.println(vertices.get(1).name + "--->" + vertices.get(1).message + "--->" + vertices.get(0).name);
            } else if (vertices.get(1).weight < vertices.get(0).weight) {
                if (vertices.get(0).message == null || vertices.get(1).message == null) {
                    if (vertices.get(0).message == null) {
                        vertices.get(0).message = "[" + vertices.get(0).name + "," + vertices.get(0).weight + "]";
                        Graph.changer(vertices.get(0).name, vertices.get(0).message, vertices.get(0).weight);
                    }
                    if (vertices.get(1).message == null) {
                        vertices.get(1).message = "[" + vertices.get(1).name + "," + vertices.get(1).weight + "]";
                        Graph.changer(vertices.get(1).name, vertices.get(1).message, vertices.get(1).weight);
                    }
                } else {
                    vertices.get(0).message = vertices.get(0).message;
                    Graph.changer(vertices.get(0).name, vertices.get(0).message, vertices.get(0).weight);
                    vertices.get(1).message = vertices.get(1).message;
                    Graph.changer(vertices.get(1).name, vertices.get(1).message, vertices.get(1).weight);
                }
                System.out.println(vertices.get(1).name + "--->" + vertices.get(1).message + "--->" + vertices.get(0).name);
            } else {
                if (vertices.get(0).message == null || vertices.get(1).message == null) {
                    if (vertices.get(0).message == null) {
                        vertices.get(0).message = "[" + vertices.get(0).name + "," + vertices.get(0).weight + "]";
                        vertices.get(0).message += "[" + vertices.get(1).name + "," + vertices.get(1).weight + "]";
                        Graph.changer(vertices.get(0).name, vertices.get(0).message, vertices.get(0).weight);
                    }
                    if (vertices.get(1).message == null) {
                        vertices.get(1).message = "[" + vertices.get(0).name + "," + vertices.get(0).weight + "]";
                        vertices.get(1).message = "[" + vertices.get(1).name + "," + vertices.get(1).weight + "]";
                        Graph.changer(vertices.get(1).name, vertices.get(1).message, vertices.get(1).weight);
                    }
                } else {
                    vertices.get(0).message += vertices.get(1).message;
                    Graph.changer(vertices.get(0).name, vertices.get(0).message, vertices.get(0).weight);
                }
                System.out.println(vertices.get(1).name + "--->" + vertices.get(1).message + "--->" + vertices.get(0).name);
            }
        }
    }

    /*This function gets the vertex/vertices in the message of the root node of the broadcast graph*/
    public static void best_node_printer() {
        System.out.print("Best node--->");
        /*Splitting the string to be able to print it*/
        String[] arrOfStr = Graph.broadcast_graph.get(Graph.broadcast_graph.size() - 1).get(1).message.split(",");
        int m;
        for (m = 0; m < arrOfStr.length - 2; m++) {
            System.out.print(arrOfStr[m].charAt(arrOfStr[m].length() - 1) + ",");
        }
        System.out.print(arrOfStr[m].charAt(arrOfStr[m].length() - 1));
    }

    /*This function traverses in the adjacency list created for the inital graph until
    it finds the root node and sends it to the traversal function*/
    public static void printBroadcastSteps() {
        System.out.println("Broadcast steps:");
        int k, j;
        for (k = 0; k < Graph.adj.size(); k++) {
            for (j = 0; j < Graph.adj.get(k).size(); j++) {
                if (Graph.adj.get(k).get(j).name.equals(Graph.root_node)) {
                    Graph.traversal(Graph.adj.get(k).get(j));
                    break;
                }
            }
        }
    }
}
