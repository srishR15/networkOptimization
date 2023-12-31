package networkOpt;

import java.util.*;

class MaxBwWithoutHeap
{
    static Print print=new Print();

    public static String[] status;
    public static Integer[] bw;
    public static Integer[] dad;

    public static int DijkstraWithoutHeap(int a, int z,Graph graph)
    {
        int x= graph.vertex;

        status = new String[x];
        bw = new Integer[x];
        dad = new Integer[x];
        for(int i=0; i<x; i++){
            status[i] = "unseen";
            bw[i] = Integer.MAX_VALUE;
        }
        status[a] = "intree";
        bw[a] = Integer.MAX_VALUE;
        dad[a] = -1;
        List<Edge> edgeList = graph.adjListEdge[a];
        for(Edge edge : edgeList){
            int neighbor = edge.target;
            status[neighbor] = "fringe";
            bw[neighbor] = edge.weight;
            dad[neighbor] = a;
        }
        while(!status[z].equals("intree"))
        {
            int bandwidthMaximum = Integer.MIN_VALUE;
            int nextVert = -1;
            for(int i=0; i<graph.vertex; i++){
                if(status[i].equals("fringe")) {
                    if (bw[i] > bandwidthMaximum)
                    {
                        bandwidthMaximum = bw[i];
                        nextVert = i;
                    }
                }
            }
            status[nextVert] = "intree";
            edgeList = graph.adjListEdge[nextVert];
            for(Edge edge : edgeList)
            {
                int neighbor = edge.getTarget();
                if(status[neighbor].equals("unseen"))
                {
                    status[neighbor] = "fringe";
                    bw[neighbor] = Math.min(bw[nextVert], edge.weight);
                    dad[neighbor] = nextVert;
                }
                else if(status[neighbor].equals("fringe") && (bw[neighbor] < Math.min(edge.weight,bw[nextVert])))
                {
                    dad[neighbor] = nextVert;
                    bw[neighbor] = Math.min(bw[nextVert], edge.weight);
                }
            }
        }
        print.printMaxBandwidthPath(dad, a, z);
        return bw[z];
    }
}

