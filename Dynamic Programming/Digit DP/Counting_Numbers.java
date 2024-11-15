import java.util.*;
import java.io.*;

public class Counting_Numbers {

    public static long[][][][] digitDP;

    public static void resetDigitDP() {
        int n = 20;
        int ext = 11;
        int zero = 2;
        int tight = 2;

        digitDP = new long[n][ext][zero][tight];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < ext; j++) {
                for (int k = 0; k < zero; k++) {
                    for (int l = 0; l < tight; l++) {
                        digitDP[i][j][k][l] = -1;
                    }
                }
            }
        }
    }

    public static long solve(int pos, int prevDigit, int leadingZero, int tight, int[] digits, int n) {
        if (pos == n) {
            return 1;
        }

        if (digitDP[pos][prevDigit][leadingZero][tight] != -1) {
            return digitDP[pos][prevDigit][leadingZero][tight];
        }

        long count = 0;

        int limit = tight == 1 ? digits[pos] : 9;

        for (int digit = 0; digit <= limit; digit++) {
            if (leadingZero == 0 && digit == prevDigit) {
                continue;
            }

            int newTight = (tight == 1 & digit == limit) ? 1 : 0;
            int newLeadingZero = (leadingZero == 1 && digit == 0) ? 1 : 0;

            count = (count + solve(pos + 1, digit, newLeadingZero, newTight, digits, n));
        }

        return digitDP[pos][prevDigit][leadingZero][tight] = count;
    }

    public static long countNumbers(long num) {
        if (num < 0) {
            return 0;
        }

        int[] digits = MyUtils.toNumIntArr(String.valueOf(num));

        resetDigitDP();

        return solve(0, 10, 1, 1, digits, digits.length);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        long L = fs.nextLong();
        long R = fs.nextLong();

        long ans = countNumbers(R) - countNumbers(L - 1);

        fs.writer().write(ans + "\n");

        fs.close();
    }

    public static int MOD = 1000000007;

    static class MyUtils {

        public static int[] toNumIntArr(String str) {
            int n = str.length();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = str.charAt(i) - '0';
            }
            return nums;
        }

        public static long power10(int exp) {
            long result = 1;
            while (exp-- > 0) {
                result = (result * 10) % MOD;
            }
            return result;
        }
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