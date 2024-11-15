import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Solution {
    static int cycleCount = 0;

    public static void dfs(int currNode, int parentNode, List<List<Integer>> adjList, int[] parent, int[] color) {
        if(cycleCount > 0) {
            return;
        }

        if (color[currNode] == 2) {
            return;
        }

        if (color[currNode] == 1) {
            ArrayList<Integer> cycle = new ArrayList<>();
            cycle.add(currNode);
            int tempNum = parentNode;
            while (tempNum != currNode) {
                cycle.add(tempNum);
                tempNum = parent[tempNum];
            }
            cycle.add(tempNum);


            System.out.println(cycle.size());
            for (int i = cycle.size()-1; i >= 0; i--) {
                System.out.print(cycle.get(i) + " ");
            }
            
            cycleCount++;
            return;
        }

        parent[currNode] = parentNode;
        color[currNode] = 1;

        for (int adjNode : adjList.get(currNode)) {
            if(cycleCount > 0) {
                return;
            }

            if (adjNode == parent[currNode]) {
                continue;
            }
            dfs(adjNode, currNode, adjList, parent, color);
        }

        color[currNode] = 2;
    }

    public static void printCycle(List<List<Integer>> adjList) {
        int n = adjList.size();

        int[] parent = new int[n];
        int[] color = new int[n];

        for(int i = 1; i < n; i++) {
            if(color[i] == 0) {
                dfs(i, -1, adjList, parent, color);
            }
        }

        if(cycleCount == 0) {
            System.out.println("IMPOSSIBLE");
        }
    }
}

public class RoundTrip {
    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int k = kb.nextInt();

        List<List<Integer>> adjList = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < k; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        Solution.printCycle(adjList);
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int k = 1;
        //k = kb.nextInt();
        while (k-- > 0) {
            solve(kb);
        }
        kb.close();
    } 
}