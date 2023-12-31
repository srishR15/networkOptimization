package networkOpt;

import java.util.*;
import static networkOpt.HeapForKruskal.heapSort;
import static networkOpt.Print.printPathForKruskal;
public class Kruskal {
    private static Graph MSpanningTree;
    private static int[] dad;
    private static int[] rank;
    private static int[] dfsKrusk;
    private static int MaxBWKruskal;
    public static void dfsForKruskal(int a, int z, boolean[] visit, ArrayList<Integer> stk, int bandW)
    {
        stk.add(a);
        if(a==z)
        {
            MaxBWKruskal = bandW;
            printPathForKruskal(stk);
            return;
        }
        visit[a] = true;
        ArrayList<Edge> edgesMST = MSpanningTree.adjListEdge[a];
        if(edgesMST.size() > 0)
        {
            for(Edge e : edgesMST)
            {
                if(visit[e.target] == false)
                {
                    dfsForKruskal(e.target, z, visit, stk, Math.min(bandW, e.weight));
                }
            }
        }
        stk.remove(stk.size() - 1);
    }
    private static int find(int i)
    {
        int w = i;
        while(dad[w]!=w)
        {
            w=dad[w];
        }
        return w;
    }
    public static void union(int u1, int u2)
    {
        if(rank[u1] > rank[u2])
        {
            dad[u2] = u1;
        }
        else if(rank[u1] < rank[u2])
        {
            dad[u1] = u2;
        }
        else
        {
            dad[u1] = u2;
            rank[u1]++;
        }
    }
    public static int MaxBWKruskal(Graph graph, int a, int z)
    {
        List<Edge> listOfSortedEdges = heapSort(graph);
        dad = new int[graph.vertex];
        rank = new int[graph.vertex];
        for(int i = 0; i< graph.vertex; i++)
        {
            dad[i] = i;
            rank[i] = 1;
        }
        MSpanningTree = new Graph(graph.vertex);
        for(Edge sortE: listOfSortedEdges)
        {
            int s= sortE.getStart();
            int t= sortE.getTarget();
            int r1 = find(s);
            int r2 = find(t);
            if(r1 != r2){
                MSpanningTree.newEdgeCreate(s, t, sortE.getWeight());
                union(r1, r2);
            }
        }
        boolean[] visit = new boolean[MSpanningTree.vertex];
        dfsKrusk = new int[MSpanningTree.vertex];
        for(int i=0; i<MSpanningTree.vertex; i++)
        {
            visit[i] = false;
            dfsKrusk[i] = -1;
        }
        ArrayList<Integer> stk = new ArrayList<>();
        int bandwidths = Integer.MAX_VALUE;
        dfsForKruskal(a, z, visit, stk, bandwidths);
        return MaxBWKruskal;
    }
}

