import java.io.*;
import java.util.ArrayList;

/*This class reads the file prints the required steps*/
public class FileRead {
    public static void FileReader(String filename,String output) throws IOException { //This function reads the file
        PrintStream ps = new PrintStream(new File(output));                        //and sends it to the related
        System.setOut(ps); //Setting system out to output.txt                       //functions to Graph class
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int line_count = 0;
        int index = 0;
        Graph.adj = new ArrayList<>(); //creating adjacency list for the graph
        Graph.broadcast_graph = new ArrayList<>(); //creating adjacency list for the broadcast steps graph
        while ((line = br.readLine()) != null) {
            String[] arrOfStr = line.split(" ");
            if (line_count == 0) {
                Graph.creating_adjacency(arrOfStr, index); //this function creates the adjacency list with root vertices
            } else if (line_count == 1) Graph.root_node = arrOfStr[0]; //keeping the root node in a string
            else {
                Graph.edge_adding(arrOfStr); //connecting the vertices together
            }
            line_count++;
        }
        Printer.printGraphStructure(Graph.adj); //printing the graph created at the beginning
        Printer.printBroadcastSteps(); //printing the broadcast steps accordingly
        Printer.message_passing_print();  //printing the messages passed by each vertex to it's parent
        Printer.best_node_printer(); //determining the best node and printing
    }
}
