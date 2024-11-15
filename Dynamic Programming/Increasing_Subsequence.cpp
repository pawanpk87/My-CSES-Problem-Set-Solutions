#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

int lengthOfLIS(vector<int>& nums) {
        
    int n=nums.size();
    
    vector<int> LIS;
    
    for(auto& curr : nums)
    {
        auto index=lower_bound(LIS.begin(),LIS.end(),curr);
        if(index == LIS.end())
            LIS.push_back(curr);
        else
            *index=curr;                
    }
    
    return LIS.size();
}

void solve()
{
    int n; cin>>n;
    vector<int> nums;
    for(int i = 0; i < n; i++){
        int x; cin>>x;
        nums.push_back(x);
    }
    cout<<lengthOfLIS(nums);
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
