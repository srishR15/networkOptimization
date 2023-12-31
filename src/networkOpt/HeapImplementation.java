package networkOpt;

class HeapImplementation
{
    public int sizeOfHeap;
    private final int[] H;
    private final int[] D;
    private final int[] P;

    public HeapImplementation(int x)
    {
        H = new int[x];
        D = new int[x];
        P = new int[x];
        sizeOfHeap = 0;
    }

    public int maxOfHeap()
    {
        return H[0];
    }
    public void swapping(int u, int v)
    {
        int h = H[u];
        H[u] = H[v];
        H[v] = h;
        int p = P[H[u]];
        P[H[u]] = P[H[v]];
        P[H[v]] = p;
    }
    public void heapInsertion(int vert, int bw)
    {
        H[sizeOfHeap] = vert;
        D[vert] = bw;
        P[vert] = sizeOfHeap;
        int cur = sizeOfHeap;
        int par = (cur-1)/2;
        while( cur > 0 && par >=0 && (D[H[par]] < D[H[cur]]))
        {
            swapping(cur, par);
            cur = par;
            par = (cur-1)/2;
        }
        sizeOfHeap++;
    }
    
    public void heapDeletion(int vert)
    {
        int p = P[vert];
        swapping(p, sizeOfHeap-1);
        H[sizeOfHeap-1] = -1;
        D[vert] = -1;
        P[vert] = -1;
        sizeOfHeap--;
        heapify(p);
    }

    public void heapify(int h)
    {
        int big = h;
        int l=2*h+1;
        int r=2*h+2;

        if(l < sizeOfHeap && D[H[l]] > D[H[big]])
        {
            big = l;
        }
        if(r < sizeOfHeap && D[H[r]] > D[H[big]])
        {
            big = r;
        }
        if(big != h)
        {
            swapping(big,h);
            heapify(big);
        }
    }
}

