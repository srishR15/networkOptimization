package networkOpt;

import java.util.List;

public class MaxBwWithHeap {
    static Print print=new Print();
    public static int DijkstraWithHeap(Graph graph, int a, int z){
        int x = graph.vertex;
        String[] status = new String[x];
        Integer[] bw = new Integer[x];
        Integer[] dad = new Integer[x];
        for(int i=0; i<x; i++)
        {
            status[i] = "unseen";
            bw[i] = Integer.MAX_VALUE;
        }
        status[a] = "intree";
        dad[a] = -1;
        HeapImplementation heapForMaxBWDijkstra = new HeapImplementation(x);
        List<Edge> edgeList = graph.adjListEdge[a];
        for(Edge edge : edgeList)
        {
            int neighbor = edge.target;
            status[neighbor] = "fringe";
            bw[neighbor] = edge.weight;
            dad[neighbor] = a;
            heapForMaxBWDijkstra.heapInsertion(neighbor, bw[neighbor]);
        }
        while(!status[z].equals("intree"))
        {
            int nextVert = heapForMaxBWDijkstra.maxOfHeap();
            status[nextVert] = "intree";
            heapForMaxBWDijkstra.heapDeletion(nextVert);
            edgeList = graph.adjListEdge[nextVert];
            for(Edge edge : edgeList)
            {
                int neighbor = edge.target;
                if(status[neighbor].equals("unseen"))
                {
                    status[neighbor]="fringe";
                    bw[neighbor] = Math.min(bw[nextVert], edge.weight);
                    dad[neighbor] = nextVert;
                    heapForMaxBWDijkstra.heapInsertion(neighbor, bw[neighbor]);
                }
                else if(status[neighbor].equals("fringe") && (bw[neighbor] < Math.min(bw[nextVert], edge.weight)))
                {
                    heapForMaxBWDijkstra.heapDeletion(neighbor);
                    dad[neighbor] = nextVert;
                    bw[neighbor] = Math.min(bw[nextVert], edge.weight);
                    heapForMaxBWDijkstra.heapInsertion(neighbor, bw[neighbor]);
                }
            }
        }
        print.printMaxBandwidthPath(dad, a, z);
        return bw[z];
    }
}
