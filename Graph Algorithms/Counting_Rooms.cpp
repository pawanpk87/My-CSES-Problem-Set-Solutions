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

void visitRoom(vector<vector<char>>& grid, int i, int j, int n, int m){
    if(i >= 0 && j >= 0 && i < n && j < m && grid[i][j] == '.'){
        grid[i][j] = '#';
        visitRoom(grid, i, j+1, n, m);
        visitRoom(grid, i, j-1, n, m);
        visitRoom(grid, i+1, j, n, m);
        visitRoom(grid, i-1, j, n, m);
    }
}

int solve(vector<vector<char>>& grid){
    int n = grid.size();
    int m = grid[0].size();

    int count = 0;

    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(grid[i][j] == '.'){
                visitRoom(grid, i, j, n, m);
                count++;
            }
        }
    }

    return count;
}

void solve(){
    int n = 1, m = 0;
    cin>>n; cin>>m;

    vector<vector<char>> grid(n, vector<char>(m, '#'));
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            char ch; cin>>ch;
            grid[i][j] = ch;
        }
    }

    int count = solve(grid);
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