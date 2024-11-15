#include <bits/stdc++.h>
using namespace std;
 
#define int long long int
 
int mod = 1000000007;
 
int solve(int x, int* price, int* pages, int n){
    int  dp[n+1][x+1];
    memset(dp, 0, sizeof dp);
 
    for(int i = 1; i <= n; i++){
        for(int j = 0; j <= x; j++){
            dp[i][j] = dp[i-1][j];
            if(j >= price[i-1]){
                dp[i][j] = max(dp[i][j],  (dp[i-1][j-price[i-1]] + pages[i-1]) % mod);
            }
        }
    }
 
    return dp[n][x];
}

 
void solve()
{
    int n,x;
    cin >> n >> x;
 
    int price[n], pages[n];
    for(int i = 0; i < n; i++){
        int num; cin>>num;
        price[i] = num;
    }
    for(int i = 0; i < n; i++){
        int num; cin>>num;
        pages[i] = num;
    }
 
    cout<<solve(x, price, pages, n)<<endl;
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
