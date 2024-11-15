import java.util.*;
import java.io.*;

public class Exponentiation {

    public static int MOD = 1000000007;

    public static long multiMod(long a, long b) {
        long na = a % MOD;
        long nb = b % MOD;
        return (na * nb) % MOD;
    }

    // O(N)
    public static long pow(long n, long k) {
        long power = 1;
        for (int i = 1; i <= k; i++) {
            power = (power * n) % MOD;
        }
        return power;
    }

    // O(N)
    public static long powWithMultiMod(long n, long k) {
        long power = 1;
        for (int i = 1; i <= k; i++) {
            power = multiMod(power, n);
        }
        return power;
    }

    // O(log k)
    public static long powModularExponentiation(long n, long k) {
        /*
            a^b
                if b is even
                    then we can write is as
                     (a^(b/2)) ^ 2 === (a*a) ^ b/2
                elsed
                     a * (a ^ b-1)
        */
        if (k == 0) {
            return 1;
        }
        if (k % 2 == 0) {
            return powModularExponentiation(multiMod(n, n), k/2);
        } else {
            return multiMod(n, powModularExponentiation(n, k - 1));
        }
    }

    // O(log k)
    public static long binaryExponentiation(long n, long k) {
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
                pow = multiMod(pow, n);
            }
            n = multiMod(n, n);
            k = k >> 1;
        }
        return pow;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();
        while (t-- > 0) {
            long n = fs.nextLong();
            long k = fs.nextLong();

            fs.writer().write(binaryExponentiation(n, k) + " \n");
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
