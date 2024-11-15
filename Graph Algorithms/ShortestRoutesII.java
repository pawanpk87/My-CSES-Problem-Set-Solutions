import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class ShortestRoutesII {
    // public static void shortest_distance(long[][] dist) {
    //     int n = dist.length;
    //     for (int intermediate = 0; intermediate < n; intermediate++) {
    //         for (int srcNode = 0; srcNode < n; srcNode++) {
    //             if(dist[srcNode][intermediate] == -1) {
    //                 continue;
    //             }
    //             for (int distNode = 0; distNode < n; distNode++) {
    //                 if (dist[intermediate][distNode] == -1) {
    //                     continue;
    //                 }
                    
    //                 if (dist[srcNode][distNode] == -1) {
    //                     dist[srcNode][distNode] = dist[srcNode][intermediate] +
    //                                               dist[intermediate][distNode];
    //                 } else {
    //                     dist[srcNode][distNode] = Math.min(dist[srcNode][distNode],
    //                                                        (dist[srcNode][intermediate] + dist[intermediate][distNode]));
    //                 }
    //             }
    //         }
    //     }
    // }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int q = Integer.parseInt(input[2]);

        long[][] dist = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        int u, v, d;
        for (int i = 0; i < m; i++) {
            // br.readLine();
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]) - 1;
            v = Integer.parseInt(input[1]) - 1;
            d = Integer.parseInt(input[2]);

            if (dist[u][v] == -1) {
                dist[u][v] = d;
            } else {
                dist[u][v] = Math.min(dist[u][v], d);
            }

            if (dist[v][u] == -1) {
                dist[v][u] = d;
            } else {
                dist[v][u] = Math.min(dist[v][u], d);
            }
        }

        //shortest_distance(dist);
 
        for (int intermediate = 0; intermediate < n; intermediate++) {
            for (int srcNode = 0; srcNode < n; srcNode++) {
                if(dist[srcNode][intermediate] == -1) {
                    continue;
                }
                for (int distNode = 0; distNode < n; distNode++) {
                    if (dist[intermediate][distNode] == -1) {
                        continue;
                    }
                    
                    if (dist[srcNode][distNode] == -1) {
                        dist[srcNode][distNode] = dist[srcNode][intermediate] +
                                                  dist[intermediate][distNode];
                    } else {
                        dist[srcNode][distNode] = Math.min(dist[srcNode][distNode],
                                                           (dist[srcNode][intermediate] + dist[intermediate][distNode]));
                    }
                }
            }
        }
        
        for (int i = 0; i < q; i++) {
            // br.readLine();
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]) - 1;
            v = Integer.parseInt(input[1]) - 1;

            bw.write(dist[u][v] + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
