import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*

    3 4
    1 2 3
    2 3 1
    1 3 7
    2 1 5


                5
        ----------------
      |                 |
      |        3        |
    city1  -------->  city2
       \                |
     7  \               |  1
         \              |
           ----------> city3


    single source distance

    1   2   3
    0   3   4

    
    minDist = 4
              |
            
            if edge is the part of route

                minDist - edge + (edge/2)


    dist [ 0, num1, num2,..... ]



    dist[u][0]   ->  minimum price to reach node 'u' without using coupon

    dist[u][1]   ->  minimum price to reach node 'u' with using coupon



    node 'u'    ------ price ----->      node 'v'

    if coupon is not taken
        dist[v][0]  = min(dist[v][0], price +  dist[u][0])
        dist[v][1]  = min(dist[v][1],  price/2 +  dist[u][0])

    if coupon is taken
        dist[v][1]  = min(dist[v][1],  price +  dist[u][1])

*/

public class FlightDiscount {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = bufferedReader.readLine().split(" ");
        
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        List<List<int[]>> adjList = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            //bufferedReader.readLine();
            input = bufferedReader.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            adjList.get(u).add(new int[]{v, d});
        }
        
        long[][] dist = new long[2][n+1];
        for(int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        
        PriorityQueue<long[]> q = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long[] b) {
                return Long.compare(a[1], b[1]);
            }
        });

        dist[0][1] = 0;

        q.add(new long[]{1, 0, 0});

        while (!q.isEmpty()) {
            long[] curr = q.poll();
            int u = (int)curr[0];
            long d = curr[1];
            int coupon = (int)curr[2];

            if(dist[coupon][u] < d) {
                continue;
            }

            for(int[] adjNode : adjList.get(u)) {
                int v = adjNode[0];
                int w = adjNode[1];
                // if coupon is not used
                if (coupon == 0) {
                    if (dist[0][v] > (w + d)) {
                        dist[0][v] = w + d;
                        q.add(new long[]{v, dist[0][v], coupon});
                    }
                    if (dist[1][v] > ((w / 2) + d)) {
                        dist[1][v] = (w / 2) + d;
                        q.add(new long[]{v, dist[1][v], coupon + 1});
                    }
                } else {
                    if (dist[1][v] > (w + d)) {
                        dist[1][v] = w + d;
                        q.add(new long[]{v, dist[1][v], coupon});
                    }
                }
            }
        }
        
        long result = Math.min(dist[0][n], dist[1][n]);
        bufferedWriter.write(String.valueOf(result));
        
        bufferedWriter.flush();

        bufferedReader.close();
        bufferedWriter.close();
    }
} 