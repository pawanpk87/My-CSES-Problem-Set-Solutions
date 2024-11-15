#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

void solve(int n) {
    int sum = (n * (n + 1)) / 2;
    if(sum%2 != 0) {
        cout<<"NO";
        return;
    }
    sum = sum / 2;
    vector<int> nums;
    vector<int> st1, st2;
    for(int i = n; i >= 1; i--) {
        if((sum - i) >= 0) {
            st1.push_back(i);
            sum -= i;
        } else {
            st2.push_back(i);
        }
    }
    cout<<"YES"<<endl;
    cout<<st1.size()<<endl;
    for(auto& a : st1) cout<<a<<" ";
    cout<<endl;
    cout<<st2.size()<<endl;
    for(auto& a : st2) cout<<a<<" ";
}

void solve()
{
    int n; cin>>n;
    solve(n);
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
