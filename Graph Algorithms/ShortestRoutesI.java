import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ShortestRoutesI {
    public static int[] dijkstra(List<List<int[]>> adjList, int n) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[1] < b[1])
                    return -1;
                if(a[1] > b[1])
                    return 1;
                return 0;
            }
        });

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        minHeap.add(new int[] {1, 0});
        dist[1] = 0;

        while (!minHeap.isEmpty()) {
            int[] currNode = minHeap.poll();
            int u = currNode[0];
            for(int[] adjNode : adjList.get(u)) {
                int v = adjNode[0];
                int wt = adjNode[1];
                if(dist[v] > (dist[u] + wt)) {
                    dist[v] = dist[u] + wt;
                    minHeap.add(new int[] {v, dist[v]});
                }
            }
        }

        return dist;
    }

    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int m = kb.nextInt();

        List<List<int[]>> adjList = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            int s,t,d;
            s = kb.nextInt();
            t = kb.nextInt();
            d = kb.nextInt();

            adjList.get(s).add(new int[] {t, d});
        }

        int[] dist = dijkstra(adjList, n);

        for(int i = 1; i < dist.length; i++) {
            if(i == dist.length-1) {
                System.out.print(dist[i]);
            } else {
                System.out.print(dist[i] + " ");
            }
        }
        
        System.out.println();
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