#include <algorithm>
#include <bitset>
#include <complex>
#include <deque>
#include <exception>
#include <fstream>
#include <functional>
#include <iomanip>
#include <ios>
#include <iosfwd>
#include <iostream>
#include <istream>
#include <iterator>
#include <limits>
#include <list>
#include <locale>
#include <map>
#include <memory>
#include <new>
#include <numeric>
#include <ostream>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <stdexcept>
#include <streambuf>
#include <string>
#include <utility>
#include <vector>
#include <array>
#include <random>
#include <tuple>
#include <unordered_map>
#include <unordered_set>

#define int long long
#define uint unsigned long long
#define vi vector<int>
#define vvi vector<vi >
#define vb vector<bool>
#define vvb vector<vb >
#define fr(i,n) for(int i=0; i<(n); i++)
#define rep(i,a,n) for(int i=(a); i<=(n); i++)
#define nl cout<<"\n"
#define dbg(var) cout<<#var<<"="<<var<<" "
#define all(v) v.begin(),v.end()
#define sz(v) (int)(v.size())
#define srt(v)  sort(v.begin(),v.end())         // sort 
#define mxe(v)  *max_element(v.begin(),v.end())     // find max element in vector
#define mne(v)  *min_element(v.begin(),v.end())     // find min element in vector
#define unq(v)  v.resize(distance(v.begin(), unique(v.begin(), v.end())));
// make sure to sort before applying unique // else only consecutive duplicates would be removed 
#define bin(x,y)  bitset<y>(x) 
using namespace std;
int MOD=1e9+7;      // Hardcoded, directly change from here for functions!

using namespace std;

void modadd(int &a , int b) {a=((a%MOD)+(b%MOD))%MOD;}
void modsub(int &a , int b) {a=((a%MOD)-(b%MOD)+MOD)%MOD;}
void modmul(int &a , int b) {a=((a%MOD)*(b%MOD))%MOD;}

vector<int> solve(vector<int>& nums, vector<vector<int>>& query){
    for(int i = 1; i < nums.size(); i++){
        nums[i] += nums[i - 1];
    }

    vector<int> ans;
    for(auto& q : query){
        int s = q[0], e = q[1];
        s--;
        ans.push_back(nums[e] - nums[s]);
    }
    return ans;
}

void solve(){
    int n = 1, m = 0;
    cin>>n; cin>>m;

    vector<int> nums;
    nums.push_back(0);
    while(n--){
        int temp; cin>>temp; nums.push_back(temp);
    }

    vector<vector<int>> query;
    while(m--){
        int s,e; cin>>s; cin>>e;
        query.push_back({s, e});
    }

    vector<int> ans = solve(nums, query);
    for(auto& a : ans) cout<<a<<endl;
    cout<<endl;
}

int32_t main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T = 1;
    //cin >> T;
    while(T--){
        solve();
    }

    return 0;
}