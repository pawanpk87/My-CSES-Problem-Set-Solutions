#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

// int solve(int sum, vector<int>& nums, int n) {
//     if(sum == 0) {
//         return 1;
//     }
//     if(n == 0 ) {
//         return 0;
//     }
//     if(nums[n-1] <= sum) {
//         return (solve(sum - nums[n-1], nums, n - 1) + solve(sum, nums, n-1)) % mod;
//     } else {
//         return solve(sum, nums, n-1);
//     }
// }

int solve(vector<int>& nums, int targetSum) {
    int n = nums.size();
    vector<vector<int>> dp(n+1, vector<int>(targetSum+1, 0));
    dp[0][0] = 1;
    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= targetSum; j++) {
            if(nums[i-1] <= j) {
                dp[i][j] = (dp[i-1][j - nums[i-1]] + dp[i-1][j]) % mod;
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[n][targetSum];
}

int solve(int n) {
    int sum = (n * (n + 1)) / 2;
    if(sum%2 != 0) {
        return 0;
    }
    sum = sum / 2;
    vector<int> nums;
    for(int i = 1; i <= n; i++) nums.push_back(i);
    return solve(nums, sum);
}

void solve()
{
    int n; cin>>n;
    cout<<solve(n);
}

int32_t main()
{
    int T = 1;
    // cin >> T;
    while (T--)
    {
        solve();
    }
    return 0;
}
