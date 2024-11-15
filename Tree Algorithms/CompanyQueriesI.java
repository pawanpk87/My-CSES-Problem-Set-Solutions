import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Company_Queries_I
 */
public class CompanyQueriesI {
    public static int maxPowerOfTwo = 20;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int q = fs.nextInt();

        int[][] far = new int[maxPowerOfTwo + 1][200001];

        int emp, mng;
        for(int i = 2; i <= n; i++) {
            emp = i;
            mng = fs.nextInt();

            far[0][emp] = mng;
        }

        for(int h = 1; h <= maxPowerOfTwo; h++) {
            for(int i = 1;  i <= n; i++) {
                far[h][i] = far[h - 1][far[h-1][i]];
            }
        }

        int x, k;
        for(int i = 1; i <= q; i++) {
            x = fs.nextInt();
            k = fs.nextInt();

            for(int h = maxPowerOfTwo; h >= 0; h--) {
                if((1 << h) <= k) {
                    x = far[h][x];
                    k -= (1 << h);
                }
            }

            if(x == 0) {
                fs.writer().write("-1\n");
            } else {
                fs.writer().write(x+"\n");
            }
        }
        
        fs.close();
    }

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

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for(int i = 0; i < n; i++) {
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
    