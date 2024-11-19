import java.util.*;
import java.io.*;

public class Subordinates {

     /* 
        ================== TLE ======================================================

        public static void dfs(int currNode, List<List<Integer>> adjList, int[] counts) {
            for (int adjNode : adjList.get(currNode)) {
                dfs(adjNode, adjList, counts);
                counts[currNode] += (counts[adjNode] + 1);
            }
        }

        public static void solve(List<List<Integer>> adjList, FastScanner fs) throws Exception {
            int n = adjList.size();

            int[] counts = new int[n];

            dfs(1, adjList, counts);

            for (int i = 1; i < n; i++) {
                fs.writer().write(counts[i] + " ");
            }
        }
    */

    // TIME LIMIT EXCEEDED

    // ================== Chinese edge representation Start =========================

    public static final int MAX_NODES = 200020;
    public static final int MAX_EDGES = 200020;

    public static int head[];
    public static EdgeData edges[];
    public static int edgeCount = 0;

    static class EdgeData {
        public int to;
        public int next;
    }

    public static void init() {
        head = new int[MAX_NODES];
        edges = new EdgeData[MAX_EDGES];
        edgeCount = 0;

        for(int i = 0; i < MAX_NODES; i++) {
            head[i] = -1;
            edges[i] = new EdgeData();
        }
    }

    public static void addEdge(int from, int to) {
        edges[edgeCount].to = to;
        edges[edgeCount].next = head[from];

        head[from] = edgeCount;

        edgeCount++;
    }

    public static void dfs(int currNode, int[] count) {
        count[currNode] = 1;

        int adjNode;
        for (int currEdgeIndex = head[currNode]; currEdgeIndex != -1; currEdgeIndex = edges[currEdgeIndex].next) {
            adjNode = edges[currEdgeIndex].to;

            dfs(adjNode, count);

            count[currNode] += count[adjNode];
        }
    }

    // ================== Chinese edge representation End  ===========================

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = Integer.parseInt(fs.readLine());

        String[] input = fs.readLine().split(" ");

        // Chinese edge representation start

        init();

        int boss;
        for (int i = 2; i <= n; i++) {
            boss = Integer.parseInt(input[i - 2]);
            addEdge(boss, i);
        }

        // Chinese edge representation end

        int[] count = new int[n + 1];

        dfs(1, count);

        for (int i = 1; i <= n; i++) {
            fs.writer().write((count[i] - 1)+" ");
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

        String readLine() {
            try {
                return br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        String nexString() {
            return next();
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