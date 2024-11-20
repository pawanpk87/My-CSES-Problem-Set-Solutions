import java.util.*;
import java.io.*;

public class TreeDistancesI {

    // ================== TLE Solution ======================================
    public static int[] diameterOfTree(ArrayList<Integer>[] adjList, int n) {
        if (n == 1) {
            return null;
        }

        Queue<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        // Find the farthest node 'b' from arbitrary node 'a'
        int nodeA = 1;

        bfs.add(nodeA);
        visited[nodeA] = true;

        int nodeB = -1;

        while (!bfs.isEmpty()) {
            int size = bfs.size();

            while (size-- > 0) {
                int currNode = bfs.poll();

                for (int adjNode : adjList[currNode]) {
                    if (!visited[adjNode]) {
                        bfs.add(adjNode);
                        visited[adjNode] = true;

                        nodeB = adjNode;
                    }
                }
            }
        }

        // Find the farthest node 'c' from node 'b'
        visited = new boolean[n + 1];

        bfs.add(nodeB);
        visited[nodeB] = true;

        int nodeC = -1;

        while (!bfs.isEmpty()) {
            int size = bfs.size();

            while (size-- > 0) {
                int currNode = bfs.poll();

                for (int adjNode : adjList[currNode]) {
                    if (!visited[adjNode]) {
                        bfs.add(adjNode);
                        visited[adjNode] = true;

                        nodeC = adjNode;
                    }
                }
            }
        }

        return new int[] { nodeB, nodeC };
    }

    public static void dfs(int currNode, int parentNode, int distance, int sourceNode, ArrayList<Integer>[] adjList,
            int[][] dist) {
        dist[currNode][sourceNode] = distance;

        for (int adjNode : adjList[currNode]) {
            if (adjNode != parentNode) {
                dfs(adjNode, currNode, distance + 1, sourceNode, adjList, dist);
            }
        }
    }

    public static int[] solve(ArrayList<Integer>[] adjList, int n) {
        int[] longestDistanceNodes = diameterOfTree(adjList, n);

        int nodeA = longestDistanceNodes[0];
        int nodeB = longestDistanceNodes[1];

        int[][] dist = new int[n + 1][2];
        /*
         * dist[i][j]
         * | \
         * node \
         * nodeA | nodeB
         * 
         * dist[2][1] 1-> means nodeA
         * 
         * distance from nodeA to node2
         */

        dfs(nodeA, -1, 0, 0, adjList, dist);
        dfs(nodeB, -1, 0, 1, adjList, dist);

        int[] maximumDistance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            maximumDistance[i] = Math.max(dist[i][0], dist[i][1]);
        }

        return maximumDistance;
    }
    // ================== TLE Solution End ===================================

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        if (n > 1) {
            ArrayList<Integer>[] adjList = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }

            int u, v;
            for (int i = 1; i < n; i++) {
                u = fs.nextInt();
                v = fs.nextInt();

                adjList[u].add(v);
                adjList[v].add(u);
            }

            int[] ans = solve(adjList, n);

            for (int i = 1; i <= n; i++) {
                fs.writer().write(ans[i] + " ");
            }

        } else {
            fs.writer().write("0 \n");
        }

        fs.writer().write("\n");

        fs.close();
    }

    public static int MOD = 1000000007;

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer("");

        String next() {
            while (!stk.hasMoreTokens()) {
                try {
                    stk = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return stk.nextToken();
        }

        BufferedWriter writer() {
            return bw;
        }

        String nexString() {
            return next();
        }

        String readLine() {
            try {
                return br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        void close() {
            try {
                bw.flush();
                bw.close();
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}