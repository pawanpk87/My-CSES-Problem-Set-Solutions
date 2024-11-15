#include <bits/stdc++.h>
using namespace std;

#define int long long int

int mod = 1000000007;

int change(int amount, vector<int>& coins) {
    int n = coins.size();
    vector<int> dp(amount + 1, 0);
    dp[0] = 1;
    for(int i = 0; i < n; i++){
        for(int j = coins[i]; j <= amount; j++){
            dp[j] = (dp[j] + dp[j - coins[i]]) % mod;
        }
    }
    return dp[amount];
}

void solve()
{
    int n, amount;
    cin >> n >> amount;

    vector<int> coins;
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        coins.push_back(x);
    }

    cout << change(amount, coins) << endl;
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