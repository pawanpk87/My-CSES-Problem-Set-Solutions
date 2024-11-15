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
#include <cstring>

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


/**
    int memo[1000001];
    int solve(int num, vector<int>& dice){
        if(num == 0){
            return 1;
        } else{
            if(memo[num] != -1){
                return memo[num];
            }
    
            int total = 0;
            
            for(int i = 0; i < 6; i++){
                int opt1 = 0;
                if(dice[i] <= num){
                    opt1 = solve(num - dice[i], dice) % MOD;
                    total = (total + opt1) % MOD;
                } 
            }
    
            return memo[num] = total;
        }
    }
    void solve(){
        int n = 1, m = 0;
        cin>>n;
        memset(memo, -1, sizeof(memo));
        vector<int> dice = {1, 2, 3, 4, 5, 6};
        int count = solve(n, dice);
        cout<<count<<endl;
    }
*/


int solve(int n, vector<int>& dice){
    vector<int> dp(n+1, 0);
    dp[0] = 1;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= 6 && i - j >= 0; j++){
            dp[i] = (dp[i] + dp[i - j]) % MOD;
        }
    }
    return dp[n];
}

void solve(){
    int n = 1, m = 0;
    cin>>n;

    vector<int> dice = {1, 2, 3, 4, 5, 6};
    int count = solve(n, dice);
    cout<<count<<endl;
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