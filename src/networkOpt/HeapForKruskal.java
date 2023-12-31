package networkOpt;

import java.util.ArrayList;
import java.util.List;

public class HeapForKruskal {
    static void heapifyForKruskal(List<Edge> edgesList, int n, int h) {
        int big=h;
        int l=2*h+1;
        int r=2*h+2;
        if ((l < n) && (edgesList.get(l).getWeight() < edgesList.get(big).getWeight()))
        {
            big = l;
        }
        if(r<n && edgesList.get(r)!=null && edgesList.get(r).getWeight() < edgesList.get(big).getWeight())
        {
            big = r;
        }
        if(big<n && big!=h)
        {
            Edge temp = edgesList.get(h);
            edgesList.set(h,edgesList.get(big));
            edgesList.set(big,temp);
            heapifyForKruskal(edgesList,n,big);
        }
    }
    public static List<Edge> heapSort(Graph graph){
        ArrayList<Edge> sortEdge = new ArrayList<>();
        for(int i=0; i<graph.vertex; i++){
            ArrayList<Edge> edgeList = graph.adjListEdge[i];
            for(Edge e : edgeList){
                sortEdge.add(e);
            }
        }
        int k = graph.getEdges();
        for(int i = k/2 -1; i>= 0; i--)
        {
            heapifyForKruskal(sortEdge, k, i);
        }
        for(int i = k-1; i>0; i--)
        {
            Edge temp = sortEdge.get(0);
            sortEdge.set(0, sortEdge.get(i));
            sortEdge.set(i, temp);
            heapifyForKruskal(sortEdge, i, 0);
        }
        return sortEdge;
    }
}
