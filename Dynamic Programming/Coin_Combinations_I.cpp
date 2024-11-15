#include <bits/stdc++.h>
using namespace std;

#define int long long int

int mod = 1000000007;

long long solve(long long amount, vector<long long>& coins, vector<long long>& memo){
    if(amount == 0){
        return 1;
    }
    if(memo[amount] != -1){
        return memo[amount];
    }
    long long count = 0;
    for(long long i = 0; i < coins.size(); i++){
        if(coins[i] <= amount){
            count = (count + solve(amount - coins[i], coins, memo) % mod) % mod;
        }
    }
    return memo[amount] = count;
}

long long solve(vector<long long>& coins, long long amount)
{
    /*
        amount = 9
        coins = 2 3 5

        0   1   2   3   4   5   6   7   8   9
        1   0   1   1   1   2   2   2   0   0


    
     */
    int n = coins.size();
    vector<long long> dp(amount + 1, 0);
    dp[0] = 1;
    for(int i = 1; i <= amount; i++){
        for(int j = 0; j < n; j++){
            if(coins[j] <= i){
                dp[i] = (dp[i] + dp[i - coins[j]]) % mod;
            }
        }
    }
    return dp[amount];
}

void solve()
{
    long long n, amount;
    cin >> n >> amount;

    //cout<<"n: " <<n<<" amount: " <<amount<<endl;

    vector<long long> coins;
    for (long long i = 0; i < n; i++)
    {
        long long x;
        cin >> x;
        coins.push_back(x);
    }

    // for(auto& c : coins) cout<<c<<" ";
    // cout<<endl;

    cout << solve(coins, amount) << endl;
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