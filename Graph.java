import java.util.*;

public class Graph {

    public static ArrayList<ArrayList<Vertex>> adj; //adjacency list for graph
    public static ArrayList<ArrayList<Vertex>> broadcast_graph; //adjacency list for broadcast graph
    public static String root_node;

    /*This function connects two vertices together*/
    static void addEdge(ArrayList<ArrayList<Vertex>> list, Vertex u, Vertex v) {
        list.get(u.index).add(v);
        list.get(v.index).add(u);
    }

    /*This function traverses through the graph and builds and prints the broadcast graph while building*/
    static void traversal(Vertex root_node) {
        ArrayList<Vertex> queue = new ArrayList<>(); //creating queue for the unvisited vertices
        queue.add(root_node);
        root_node.visited = true;
        int index = 0;
        while (!queue.isEmpty()) {
            int control_count = 0;
            Vertex main_node = queue.remove(0);
            for (Vertex control : adj.get(main_node.index)) { //traversing in the root vertices
                if (control.visited) control_count++;
            }
            if (control_count != adj.get(main_node.index).size()) { //printing if a vertex has any unvisited nodes
                System.out.print(main_node.name + "-->");
            } else continue;

            for (Vertex adjacent : adj.get(main_node.index)) {
                if (!adjacent.visited) { //checking if a vertex is visited before
                    queue.add(adjacent);
                    Vertex vertex = new Vertex(adj.get(main_node.index).get(0).name, adj.get(main_node.index).get(0).weight, index, adj.get(main_node.index).get(0).visited);
                    ArrayList<Vertex> new_graph_element_1 = new ArrayList<>();
                    new_graph_element_1.add(vertex);
                    broadcast_graph.add(index, new_graph_element_1); //adding to the broadcast graph adjacency list
                    broadcast_graph.get(index).add(adjacent);
                    System.out.print(adjacent.name + " ");
                    adjacent.visited = true; //marking the vertex as visited
                }
            }
            System.out.println();
        }
    }


    /*This function uses addEdge() function and connects two vertices*/
    static void edge_adding(String[] arrOfStr) {
        Vertex one = null;
        Vertex two = null;
        for (ArrayList<Vertex> vertices : adj) {
            if (vertices.get(0).name.equals(arrOfStr[0])) {
                one = vertices.get(0);
            } else if (vertices.get(0).name.equals(arrOfStr[1])) {
                two = vertices.get(0);
            }
        }
        assert one != null;
        addEdge(adj, one, two);
    }

    /*This functions adds the root nodes to the graph adjacency list*/
    static void creating_adjacency(String[] arrOfStr, int index) {
        for (int i = 0; i < arrOfStr.length; i = i + 2) {
            Vertex vertex = new Vertex(arrOfStr[i], Integer.parseInt(arrOfStr[i + 1]), index, false);
            index++;
            ArrayList<Vertex> to_be_added = new ArrayList<>();
            to_be_added.add(vertex);
            adj.add(vertex.index, to_be_added);
        }
    }

    /*In message passing this function changes the message and the weights of the vertices with the same name accordingly*/
    static void changer(String vertex_name, String message, int weight) {
        for (ArrayList<Vertex> vertices : broadcast_graph) {
            if (vertices.get(0).name.equals(vertex_name)) {
                vertices.get(0).message = message;
                vertices.get(0).weight = weight;
            } else if (vertices.get(1).name.equals(vertex_name)) {
                vertices.get(1).message = message;
                vertices.get(1).weight = weight;
            }
        }
    }
}
