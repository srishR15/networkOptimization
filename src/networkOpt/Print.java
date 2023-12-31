package networkOpt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Print {
    void printMaxBandwidthPath(Integer[] dad, int a, int z) {
        Stack<Integer> stk = new Stack<>();
        List<Integer> pathForMaxBw = new ArrayList<>();
        stk.push(z);
        int i = z;
        while(dad[i] != -1){
            stk.push(dad[i]);
            i = dad[i];
        }
        while(!stk.empty()){
            pathForMaxBw.add(stk.pop());
        }

        System.out.println("The maximum bandwidth path from " + a + " to " + z + " is== ");
        for(int j = 0; j< pathForMaxBw.size()-1; j++){
            System.out.print(pathForMaxBw.get(j) + " -> ");
        }
        System.out.print(pathForMaxBw.get(pathForMaxBw.size()-1));
    }
    public static void printPathForKruskal(ArrayList<Integer> stk){
        for(int i = 0; i< stk.size()-1; i++){

            System.out.print(stk.get(i) + " -> ");
        }
        System.out.print(stk.get(stk.size() - 1));
    }
}
