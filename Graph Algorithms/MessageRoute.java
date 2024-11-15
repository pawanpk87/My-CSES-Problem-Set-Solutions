import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


class Solution {
    public void solve(List<List<Integer>> adjList) {
        int n = adjList.size();
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        int[] parent = new int[n];

        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int currNode = q.poll();
            for(int adjNode : adjList.get(currNode)) {
                if(visited[adjNode] == false) {
                    dist[adjNode] = dist[currNode] + 1;
                    parent[adjNode] = currNode;
                    visited[adjNode] = true;
                    q.add(adjNode);
                }
            }
        }
        
        if(visited[n-1] == false) {
            System.out.println("IMPOSSIBLE");
            return;
        } 
        
        int parentNode = n-1;
        int totalDist = dist[n-1];
        int[] pathRes = new int[totalDist + 1];
        for(int i = totalDist; i >= 0; i--) {
            pathRes[i] = parentNode;
            parentNode = parent[parentNode];
        }

        System.out.println(totalDist + 1);
        for(int i = 0; i < pathRes.length; i++) {
            System.out.print(pathRes[i] + " ");
        }
        System.out.println();
    }
    
    public static void printAdjList(List<List<Integer>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + 1 + ": ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(adjList.get(i).get(j) + 1 + " ");
            }
            System.out.println();
        }
    }
}

public class MessageRoute {
    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int m = kb.nextInt();
        
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        int src, dest;
        for (int i = 0; i < m; i++) {
            src = kb.nextInt();
            dest = kb.nextInt();
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        new Solution().solve(adjList);
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