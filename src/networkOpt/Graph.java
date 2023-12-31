package networkOpt;
import java.util.*;
class Edge
{
    public int start, target, weight;
    Edge(int s, int t, int w)
    {
        start = s;
        target = t;
        weight = w;
    }
    Edge()
    {

    }
    public int getStart() {
        return start;
    }

    public int getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
}

class Graph {
    int vertex;

    int edges;

    ArrayList<Edge>[] adjListEdge;

    //constructor
    Graph(int vertex) {
        this.vertex = vertex;
        this.edges = 0;
        adjListEdge = new ArrayList[vertex];
        for (int i = 0; i < vertex; i++) {
            adjListEdge[i] = new ArrayList<>();
        }
    }
    public  boolean edgeExists(int u, int v) {

        ArrayList<Edge> edges = adjListEdge[u];
        if (edges.size() > 0) {
            for (Edge edge : edges) {
                if (edge.target == v)
                    return true;
            }
        }
        return false;
    }
    public  void newEdgeCreate(int start, int target, int weight) {
        if (!edgeExists(start, target)) {
            Edge edge1 = new Edge(start, target, weight);
            Edge edge2 = new Edge(target, start, weight);
            adjListEdge[start].add(edge1);
            adjListEdge[target].add(edge2);
            edges +=2;
        }
    }
    public  int degreeOfVertex(int vert)
    {
        ArrayList<Edge> edges = adjListEdge[vert];
        return edges.size();
    }
    public  void printGraph() {
        for (int i = 0; i < vertex; i++) {
            ArrayList<Edge> edges = adjListEdge[i];

            for (Edge edge : edges) {
                System.out.println("vertex " + i + " is connected to -> " + edge.target + " with weight " + edge.getWeight());
            }
        }
    }
    public  int getEdges() {
        return edges;
    }

    public static void printEdges(List<Edge> sortedEdges)
    {
        for(int i=1; i<sortedEdges.size(); i++){
            System.out.println(sortedEdges.get(i).start + "->" + sortedEdges.get(i).getTarget() + " weight " + sortedEdges.get(i).getWeight());
        }
    }

}
