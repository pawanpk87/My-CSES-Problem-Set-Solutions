#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;
int dp[1000001][2];

void computeWays() {
    /*
        https://www.youtube.com/watch?v=51AkVn5Urno

        n = 1
                    [ o(orange type of line) ]    [ g(green type of line) ]

        2 ways of going orange type of line to another orange type of line
        1 way of going orange type of line to green type of line
        1 way of going green type of line to orange type of line
        4 ways of going green type of line to green type of line

        dp[i][0] = number of ways to add rectangle to the 2*1 squares such that the last 2 squares are united
        dp[i][1] = number of ways to add rectangle to the 2*1 squares such that the last 2 squares are not united
    */

    dp[1][0] = dp[1][1] = 1;

    for (int i = 2; i < 1000001; i++) {
        dp[i][0] = (dp[i][0] + 2 * dp[i - 1][0]) % mod;
        dp[i][1] = (dp[i][1] + dp[i - 1][0]) % mod;
        dp[i][0] = (dp[i][0] + dp[i - 1][1]) % mod;
        dp[i][1] = (dp[i][1] + 4 * dp[i - 1][1]) % mod;
    }
}

void solve() {
    int n;
    cin >> n;

    cout << (dp[n][0] + dp[n][1])%mod << endl;
}

int32_t main() {
    int T;
    cin >> T;

    computeWays();
    
    while (T--) {
        solve();
    }
    return 0;
}
