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

void printAdjList(vector<int> adjList[], int n){
    for(int i = 0; i < n; i++){
        cout<<i<<" -> ";
        for(auto& adjNode : adjList[i]){
            cout<<adjNode<<" ";
        }
        cout<<endl;
    }
}


int traverse(int currNode, vector<int> adjList[], vector<int>& subordinates){
    if(adjList[currNode].size() == 0){
        return 0;
    }
    int totalSubordinates = 0;
    for(auto& adjNode : adjList[currNode]){
        totalSubordinates += (1 + traverse(adjNode, adjList, subordinates));
    }
    return subordinates[currNode] = totalSubordinates;
}

vector<int> getSubordinates(vector<int> adjList[], int n){
    vector<int> subordinates(n, 0);
    //printAdjList(adjList, n);
    traverse(1, adjList, subordinates);
    return subordinates;
}

void solve(){
    int n = 1, m = 0;
    cin>>n;
    
    /**
     * employees(n)
     * direct boos(n-1)
     * 
     *  empl    1   2   3   4   5
     *  boss        1   1   2   3
     * 
     *              1
     *            /    \
     *          2        3
     *         /         /
     *        4         5
     * 
     *  subordinates 4  1   1   0   0
     * 
    */

    vector<int> adjList[n+1];
    for(int i = 1; i < n; i++){
        int emp; cin>>emp; 
        adjList[emp].push_back(i+1);
    }

    vector<int> subordinates = getSubordinates(adjList, n+1);
    for(int i = 1; i < subordinates.size(); i++) cout<<subordinates[i]<<" ";
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