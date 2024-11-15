import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class Solution {
    public static boolean dfs(int currNode, int parent, List<List<Integer>> adjList, int[] color) {
        for(int adjNode : adjList.get(currNode)) {
            if(adjNode == parent) {
                continue;
            }
            if(color[adjNode] == -1) {
                color[adjNode] = color[currNode] == 1 ? 2 : 1;
                if(dfs(adjNode, currNode, adjList, color) == false) {
                    return false;
                }
            } else if(color[adjNode] == color[currNode]){
                return false;
            }
        }
        return true;
    }

    public static void solve(int n, int[][] friendships) {
        List<List<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int[] friend : friendships) {
            int a = friend[0];
            int b = friend[1];

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        int[] color = new int[n+1];
        Arrays.fill(color, -1);

        for(int i = 1; i <= n; i++) {
            if(color[i] ==  -1) {
                color[i] = 1;
                if(dfs(i, -1, adjList, color) == false) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            System.out.print(color[i] + (i == n ? "" : " "));
        }
        
        System.out.println();
    }
}

public class BuildingTeams {

    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int k = kb.nextInt();

        int[][] friendships = new int[k][2];

        for(int i = 0; i < k; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();

            friendships[i] = new int[] {a, b};
        }

        Solution.solve(n, friendships);
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