import java.util.*;
import java.io.*;

public class TreeDiameter {

    // ============================ Simple DFS (TLE - only one test giving TIME LIMIT EXCEEDED)========================
    public static int diameter = 0;

    public static int dfs(int currNode, int parentNode, List<List<Integer>> adjList) {
        if (adjList.get(currNode).size() == 0) {
            return 1;
        }

        int maxDepth1 = 0;
        int maxDepth2 = 0;

        for (int adjNode : adjList.get(currNode)) {
            if(adjNode == parentNode) {
                continue;
            }

            int currChildDepth = dfs(adjNode, currNode, adjList);

            if (currChildDepth > maxDepth1) {
                maxDepth2 = maxDepth1;
                maxDepth1 = currChildDepth;
            } else if (currChildDepth > maxDepth2) {
                maxDepth2 = currChildDepth;
            }
        }

        diameter = Math.max(diameter, maxDepth1 + maxDepth2);

        return 1 + maxDepth1;
    }
    // ===================================================================================================


    // ============================ 2 DFS Solution Start (ACCEPTED Solution) ==========================
    public static int diameterOfTree(ArrayList<Integer>[] adjList, int n) {
        if(n ==  1) {
            return 0;
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

                for(int adjNode : adjList[currNode]) {
                    if(!visited[adjNode]) {
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

        int dist = 0;

        while (!bfs.isEmpty()) {
            int size = bfs.size();
            int count = 0;

            while (size-- > 0) {
                int currNode = bfs.poll();

                for(int adjNode : adjList[currNode]) {
                    if(!visited[adjNode]) {
                        count++;

                        bfs.add(adjNode);
                        visited[adjNode] = true;

                        nodeB = adjNode;
                    }
                }
            }

            if(count > 0) {
                dist++;
            }
        }

        return dist;
    }
    // ============================ 2 DFS Solution End ==========================
    

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

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

        int diameter = diameterOfTree(adjList, n);

        fs.writer().write(diameter + " \n");

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