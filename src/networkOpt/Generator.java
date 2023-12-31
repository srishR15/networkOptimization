package networkOpt;

import java.util.*;
import static networkOpt.GraphStructCycle.getGraphStruct;
import static networkOpt.GraphStructCycle.weightLimit;

class Generator {

    static Random rand = new Random();
    public static Graph graph1GenerateSparse(int numbOfVertex, int degree)
    {
        Graph graph = new Graph(numbOfVertex);
        Graph graph1 = getGraphStruct(graph, numbOfVertex);
        for(int i = 0; i<numbOfVertex; i++)
        {
            int targ, weight;
            while(graph1.degreeOfVertex(i) < degree)
            {
                targ = Math.abs(rand.nextInt() % numbOfVertex);
                weight = Math.abs(rand.nextInt() % weightLimit)+1;
                 if((targ!=i) && (!graph1.edgeExists(i, targ)) && (graph1.degreeOfVertex(targ)<degree))
                 {
                    graph1.newEdgeCreate(i, targ, weight);
                 }
            }
        }
        return graph1;
    }

    public static Graph graph2GenerateDense(int numbOfVertex, int probability)
    {
        Graph graph = new Graph(numbOfVertex);
        Graph graph2 = getGraphStruct(graph, numbOfVertex);

        for(int i = 0; i< numbOfVertex; i++)
        {
            int prob, weight;
            for(int j = 0; j< numbOfVertex; j++)
            {
                prob = Math.abs(rand.nextInt() % 100);
                weight = Math.abs(rand.nextInt() % weightLimit)+1;
                if((prob < probability) && (i!=j))
                {
                    graph2.newEdgeCreate(i, j, weight);
                }
            }
        }
        return graph2;
    }

}