#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

int dp[501][501];

void preCalculate() {
    /*
        Given an a * b rectangle, your task is to cut it into squares. 
        On each move you can select a rectangle and cut it into two rectangles 
        in such a way that all side lengths remain integers. What is the minimum 
        possible number of moves ?

        n = 3 m = 5
        
        +-----+-----+-----+-----+-----+
        |     |     |     |     |     |
        |     |     |     |     |     |
        |     |     |     |     |     |
        +-----+-----+-----+-----+-----+
        |     |     |     |     |     |
        |     |     |     |     |     |
        |     |     |     |     |     |
        +-----+-----+-----+-----+-----+
        |     |     |     |     |     |
        |     |     |     |     |     |
        |     |     |     |     |     |
        +-----+-----+-----+-----+-----+


        +-----+-----+-----+     +-----+-----+
        |     |     |     |     |     |     |
        |     |     |     |     |     |     |
        |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+-----+
        |     |     |     |     |     |     |
        |     |     |     |     |     |     |
        |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+-----+
        |     |     |     |     |     |     |
        |     |     |     |     |     |     |
        |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+-----+
        (3x3 square)         (3x2 rectangle)



        +-----+-----+-----+     +-----+     +-----+
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+     +-----+
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+     +-----+
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+     +-----+
        (3x3 square)         (1x2)       (1x2)


        +-----+-----+-----+     +-----+     +-----+
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+     +-----+
        |     |     |     |     +-----+     +-----+
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+     +-----+
        |     |     |     |     +-----+     +-----+
        |     |     |     |     |     |     |     |
        |     |     |     |     |     |     |     |
        +-----+-----+-----+     +-----+     +-----+
        (3x3 square)         (1x1)       (1x1)


    */

    for(int i = 0; i < 501; i++) {
        for(int j = 0; j < 501; j++) {
            dp[i][j] = INT_MAX;
            if(i == j) {
                dp[i][j] = 0;
            } else {
                // horizotal cuts
                for(int k = 1; k < j; k++) { 
                    dp[i][j] = min(dp[i][j], 1 + dp[i][k] + dp[i][j - k]);
                }
                // vertical cuts
                for(int k = 1; k < i; k++) {
                    dp[i][j] = min(dp[i][j], 1 + dp[k][j] + dp[i - k][j]);
                }
            }
        }
    }
}

void solve()
{
    int n, m;
    cin >> n>>m;
    cout<<dp[n][m]<<endl;
}

int32_t main()
{
    int T = 1;
    // cin >> T;
    preCalculate();
    while (T--)
    {
        solve();
    }
    return 0;
}
