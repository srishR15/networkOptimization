package networkOpt;

import java.util.Random;

public class TestingProject
{
    static int VertexNumb=5000; //To allot random vertices as start and target
    static int GraphNumb=5; //number of random graphs to be made
    static int StartToTargetNumb=5;

    public static void main(String[] args)
    {
        MaxBwWithoutHeap maxBwWithoutHeap = new MaxBwWithoutHeap();
        MaxBwWithHeap maxBwWithHeap=new MaxBwWithHeap();
        Kruskal kruskal=new Kruskal();
        Generator generator=new Generator();
        for(int g=0;g<GraphNumb;g++)
        {
            System.out.println("***Test case for graph*** "+(g+1));
            long timeInitial= System.nanoTime();
            Graph graph1=generator.graph1GenerateSparse(5000,6);
            long timeGraph1=System.nanoTime();
            System.out.println("Time to generate graph1 is:- "+ (timeGraph1-timeInitial)+ "\n");

            for (int h=0;h<StartToTargetNumb;h++)
            {
                System.out.println("Vertices testing:- "+(h+1));
                long timeInitial1=System.nanoTime();
                Random rand=new Random();
                int startVert=rand.nextInt(VertexNumb);
                int targetVert=-1;
                while (true)
                {
                    targetVert=rand.nextInt(VertexNumb);
                    if(startVert!=targetVert)
                    {
                        break;
                    }
                }
                System.out.println("Start vertex: "+ startVert+" target Vertex: "+ targetVert);
                int bandwidthQ1= maxBwWithoutHeap.DijkstraWithoutHeap(startVert,targetVert,graph1);
                long bandwidthQ1Time=System.nanoTime();
                System.out.println("\n Max bw path without heap time: "+(bandwidthQ1Time-timeInitial1));
                int bandwidthQ2=maxBwWithHeap.DijkstraWithHeap(graph1,startVert,targetVert);
                long bandwidthQ2Time=System.nanoTime();
                System.out.println("\n Max bw path with heap time: "+(bandwidthQ2Time-bandwidthQ1Time));
                int bandwidthQ3=kruskal.MaxBWKruskal(graph1,startVert,targetVert);
                long bandwidthQ3Time=System.nanoTime();
                System.out.println("\n Max bw path with Kruskal: "+(bandwidthQ3Time-bandwidthQ2Time));
                System.out.println("Max bw for Q1- "+ bandwidthQ1+ " Q2- "+bandwidthQ2+" Q3- "+bandwidthQ3);
            }
            long startTime=System.nanoTime();
            Graph graph2=generator.graph2GenerateDense(5000,20);
            long endTime=System.nanoTime();
            System.out.println("Time to generate graph2 is:- "+ (endTime-startTime)+ "\n");
            for(int h=0;h<StartToTargetNumb;h++)
            {
                System.out.println("Vertices testing:- "+(h+1));
                Random rand=new Random();
                int startVert=rand.nextInt(VertexNumb); //for start and target vertex
                int targetVert=-1;
                while(true)
                {
                    targetVert=rand.nextInt(VertexNumb);
                    if(startVert!=targetVert)
                    {
                        break;
                    }
                }
                System.out.println("Start vertex: "+ startVert+" target Vertex: "+ targetVert);
                startTime=System.nanoTime();
                int bandwidthQ1= maxBwWithoutHeap.DijkstraWithoutHeap(startVert,targetVert,graph2);
                endTime=System.nanoTime();
                System.out.println("\n Max bw path without heap time: "+(endTime-startTime));
                startTime=System.nanoTime();
                int bandwidthQ2=maxBwWithHeap.DijkstraWithHeap(graph2,startVert,targetVert);
                endTime =System.nanoTime();
                System.out.println("\n Max bw path with heap time: "+(endTime-startTime));
                startTime=System.nanoTime();
                int bandwidthQ3=kruskal.MaxBWKruskal(graph2,startVert,targetVert);
                endTime =System.nanoTime();
                System.out.println("\n Max bw path with Kruskal: "+(endTime-startTime));
                System.out.println("Max bw for Q1- "+ bandwidthQ1+ " Q2- "+bandwidthQ2+" Q3- "+bandwidthQ3);
            }
        }
    }
}
