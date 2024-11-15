#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

int memo[5000][5000][2];

int solve(int sIndex, int eIndex, int turn, int nums[]) {
    if(eIndex == sIndex) {
        return turn == true ? nums[eIndex] : 0;
    }
    
    if(memo[sIndex][eIndex][turn] != -1) {
        return memo[sIndex][eIndex][turn];
    }

    if(turn == true) {
        int posSum1 = nums[sIndex] + solve(sIndex + 1, eIndex, turn == 1 ? 0 : 1, nums);
        int posSum2 = nums[eIndex] + solve(sIndex, eIndex - 1, turn == 1 ? 0 : 1, nums);
        return memo[sIndex][eIndex][turn] = max(posSum1, posSum2);
    } else {
        int posSum1 = solve(sIndex + 1, eIndex, turn == 1 ? 0 : 1, nums);
        int posSum2 = solve(sIndex, eIndex - 1, turn == 1 ? 0 : 1, nums);
        return memo[sIndex][eIndex][turn] = min(posSum1, posSum2);
    }
}

int solve(int nums[], int n) {
    memset(memo, -1, sizeof(memo));
    return solve(0, n-1, true, nums);
}

void solve()
{
    int n; cin>>n;
    int nums[n];
    for(int i = 0; i < n; i++) {
        int x; cin>>x;
        nums[i] = x;
    }
    cout<<solve(nums, n);
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
