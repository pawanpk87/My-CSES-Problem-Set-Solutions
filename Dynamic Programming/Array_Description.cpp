#include <bits/stdc++.h>
using namespace std;

#define int long long int

int mod = 1000000007;

int solve(int arr[], int n, int m) {
    /**
     dp[i][j]   ->  number of ways upto index i if we fill j = arr[i] 


        arr[i] == 0 
            if arr[i] = 0 then we have poss from 1 to m
                1   2   3   .   .   .   . m
                    dp[i][j] -> numner of ways upto index i if we fill number j
                        dp[i][j] = dp[i-1][j - 1]   + dp[i-1][j] + dp[i-1][j + 1];

        arr[i] != 0
            mean we don't have any poss
                so if we have some num
                          ? <-     num     -> ?
                          num-1    num    num + 1     

                dp[i][arr[i]] = dp[i-1][arr[i] - 1] + dp[i-1][arr[i]] + dp[i-1][arr[i] + 1];

     */
    
    int dp[n][m+2];
    memset(dp, 0, sizeof dp);

    if(arr[0] > 0){
        dp[0][arr[0]] = 1;
    } else {
        for(int j = 1; j <= m; j++){
            dp[0][j] = 1;
        }
    }

    for(int i = 1; i < n; i++){
        if(arr[i] == 0) {
            for(int j = 1; j <= m; j++){
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j + 1] ) % mod;
            }
        } else {
            dp[i][arr[i]] = (dp[i-1][arr[i] - 1] + dp[i-1][arr[i]] + dp[i-1][arr[i] + 1]) % mod;
        }
    }

    int ans = 0;
    for(int j = 1; j <= m; j++){
        ans = (ans + dp[n-1][j]) % mod;
    }

    return ans;
}

void solve()
{
    int n, m;
    cin >> n >> m;
    
    int arr[n];
    for(int i = 0; i < n; i++) {
        int x;cin>>x;
        arr[i] = x; 
    }

    cout<<solve(arr, n, m)<<endl;
}

int32_t main()
{
    long long T = 1;
    // cin>>T;
    while (T--)
    {
        solve();
    }
    return 0;
}