import java.util.*;
import java.io.*;

public class BinomialCoefficients {
    public static int MOD = 1000000007;

    public static int MAXN = 1000000;

    public static long[] fac = new long[MAXN + 1];
    public static long[] inv = new long[MAXN + 1];

    // precompute n! from 0 to MAXN with given modulo m
    public static void factorial(long m) {
        fac[0] = 1;
        for(int i = 1; i <= MAXN; i++) {
            fac[i] = (fac[i - 1] * i) % m; 
        }
    }

    // precompute all modular inverse factorials
    // from 0 to MAXN in O(n + log m)
    public static void inverse(long m) {
        inv[MAXN] = binaryExponentiation(fac[MAXN], m - 2, m);
        for(int i = MAXN; i >= 1; i--) {
            inv[i - 1] = (inv[i] * i) % m; 
        }
    }

    public static void init() {
        factorial(MOD);
        inverse(MOD);
    }

    public static long choose(long n, long k, long m) {
        return ((fac[(int) n] * inv[(int) k]) % m * inv[(int) (n - k)]) % m;
    }
 
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        init();

        while (t-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();
            
            fs.writer().write(choose(n, k, MOD) + "\n");
        }

        fs.close();
    }

    // ============================= Binary Exponentiation Start =========================
    public static long binaryExponentiation(long n, long k, long mod) {
        /*
            Binary exponentiation is a method to calculate powers like a^n efficiently.

            Instead of multiplying a by itself n−1 times (which takes a long time when n is large), 
            binary exponentiation uses a smarter approach that only requires about log n multiplications.

            Binary exponentiation makes use of the binary representation of the exponent n to break the 
            problem into smaller parts.

            a^13
                we can write 13 =  1101 base 2 in binary representation
                which means  2^3 + 2^2 + 2^0 (8 + 4 + 1)

                so instend of multiplications, we can calculate a^8 + a^2 + a^0
        */
        long pow = 1;
        while (k > 0) {
            if((k & 1) != 0) {
                pow = multiMod(pow, n, mod);
            }
            n = multiMod(n, n, mod);
            k = k >> 1;
        }
        return pow;
    } 

    public static long multiMod(long a, long b, long mod) {
        long na = a % mod;
        long nb = b % mod;
        return (na * nb) % mod;
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
