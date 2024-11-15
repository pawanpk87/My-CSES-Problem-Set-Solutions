#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

/*
    Exponential time complexity

void getAllPairs(vector<int>& nums, int sum, int k, set<int>& sums, unordered_set<int>& visited, int n) {
    for(int i = 0;  i < n; i++) {
        if(visited.count(i) == 0) {
            if(k == 1) {
                sums.insert(sum + nums[i]);
            } else {
                visited.insert(i);
                getAllPairs(nums, sum + nums[i], k - 1,  sums, visited, n);
                visited.erase(i);
            }   
        }
    }
}

void solve(vector<int>& nums) {
    int n = nums.size();
    unordered_set<int> visited;
    set<int> sums;
    
    for(int num : nums) sums.insert(num);

    for(int i = 0; i < n; i++) {
        visited.insert(i);
        for(int k = 1; k < n; k++){
            getAllPairs(nums, nums[i], k, sums, visited, n);
        }
        visited.erase(i);
    }

    cout<<sums.size()<<endl;
    for(auto& sum : sums){
        cout<<sum<<" ";
    }
    cout<<endl;
}

*/

void solve(vector<int>& nums) {
    int n = nums.size();
    
    int sum = accumulate(nums.begin(), nums.end(), 0);

    bool dp[n+1][sum+1];
    memset(dp, false, sizeof(dp));

    dp[0][0] = true;

    for(int i = 1; i <= n; i++) {
        for(int j = 0; j <= sum ; j++) {
            dp[i][j] = dp[i-1][j];
            if(j >= nums[i-1] && dp[i-1][j- nums[i-1]]) {
                dp[i][j] = true;
            }
        }
    }

    int count = 0;
    for(int j = 1; j <= sum; j++) {
        if(dp[n][j] == true) {
            count++;
        }
    }

    cout<<count<<endl;
    for(int j = 1; j <= sum; j++) {
        if(dp[n][j] == true) {
            cout<<j<<" ";
        }
    }
    cout<<endl;
}

void solve()
{
    int n; cin>>n;
    vector<int> nums;
    for(int i = 0; i < n; i++){
        int x; cin>>x;
        nums.push_back(x);
    }
    solve(nums);
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
