import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private void dfs(int currNode, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[currNode] = true;
        for(int adjNode : adjList.get(currNode)) {
            if(visited[adjNode] == false) {
                dfs(adjNode, adjList, visited);
            }
        }
    }

    public void solve(int cities, int[][] roads) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < cities; i++) {
            adjList.add(new ArrayList<>());
        }

        int src, dest;
        for(int[] road : roads) {
            src = road[0];
            dest = road[1];
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
        
        boolean[] visited = new boolean[cities];
        List<Integer> newRoads = new ArrayList<>();
        int componentCounts = 0;
        for(int citie = 0; citie < cities; citie++) {
            if (visited[citie] == false) {
                dfs(citie, adjList, visited);
                componentCounts++;
                newRoads.add(citie);
            }
        }

        --componentCounts;

        System.out.println(componentCounts);
        for(int i = 1; i <= componentCounts; i++) {
            System.out.println((newRoads.get(i-1) + 1) + " " + (newRoads.get(i) + 1));
        }
    }
}

public class BuildingRoads {
    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int m = kb.nextInt();
        
        int[][] roads = new int[m][2];
        int src, dest;
        for(int i = 0; i < m; i++) {
            src = kb.nextInt();
            dest = kb.nextInt();
            roads[i][0] = src - 1;
            roads[i][1] = dest - 1;
        }

        new Solution().solve(n, roads);
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