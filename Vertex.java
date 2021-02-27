import java.util.ArrayList;

public class Vertex {
    String name;
    int weight;
    int index;
    boolean visited;
    String message;

    public Vertex(String name, int weight, int index, boolean visited) {
        this.index = index;
        this.weight = weight;
        this.name = name;
        this.visited = visited;
    }
}
