package networkOpt;

import java.util.Random;
public class GraphStructCycle {
    static Random rand=new Random();
    static int weightLimit=100;
    public static Graph getGraphStruct(Graph graph, int numberOfVertex)
    {
        for(int g= 0; g<numberOfVertex-1; g++)
        {
            graph.newEdgeCreate(g, g+1, Math.abs(rand.nextInt() % weightLimit)+1);
        }
        graph.newEdgeCreate(0, numberOfVertex-1, Math.abs(rand.nextInt() % 100)+1);
        return graph;
    }
}
