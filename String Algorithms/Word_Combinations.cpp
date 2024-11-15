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
#define vvi vector<vi>
#define vb vector<bool>
#define vvb vector<vb>
#define fr(i, n) for (int i = 0; i < (n); i++)
#define rep(i, a, n) for (int i = (a); i <= (n); i++)
#define nl cout << "\n"
#define dbg(var) cout << #var << "=" << var << " "
#define all(v) v.begin(), v.end()
#define sz(v) (int)(v.size())
#define srt(v) sort(v.begin(), v.end())         // sort
#define mxe(v) *max_element(v.begin(), v.end()) // find max element in vector
#define mne(v) *min_element(v.begin(), v.end()) // find min element in vector
#define unq(v) v.resize(distance(v.begin(), unique(v.begin(), v.end())));
// make sure to sort before applying unique // else only consecutive duplicates would be removed
#define bin(x, y) bitset<y>(x)
using namespace std;
int MOD = 1e9 + 7; // Hardcoded, directly change from here for functions!

using namespace std;

void modadd(int &a, int b) { a = ((a % MOD) + (b % MOD)) % MOD; }
void modsub(int &a, int b) { a = ((a % MOD) - (b % MOD) + MOD) % MOD; }
void modmul(int &a, int b) { a = ((a % MOD) * (b % MOD)) % MOD; }

void printAdjList(vector<int> adjList[], int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << i << " -> ";
        for (auto &adjNode : adjList[i])
        {
            cout << adjNode << " ";
        }
        cout << endl;
    }
}

/*
Time: O(M ^ N)

int solve(string str, vector<string>& words){
    if(str.size() == 0){
        return 1;
    }

    int count = 0;

    for(auto& word : words){
        if(word.size() <= str.size() &&
           str.substr(0, word.size()) == word){
           count = (count + solve(str.substr(word.size()), words) % MOD) % MOD;
        }
    }

    return count;
}
*/

/*


    dp[i] = stores the number of way to form the substring(i, n-1)

    [0, 1, 2,  . . . . n-2, n-1, n]
                       |
                       <-----------

                      dp[i] = d[j + 1]


    Input:
            ababc
            4
            ab
            abab
            c
            cb


            [a, null, c, . . . null]
            /           \
    [null, b, ... null]  \
           |               \[null, b, ...null]
            \
             [a,null, ... null]
              |
               \
                [null, b, ...null]
                       |
                       |
                       [null, null, c,....null]




                        for empty
                        string there
                        is one possible ans
                      |
       0  1  2  3  4  5
    dp[1, 0, 0, 0, 1, 1]
                |
                countWays(4, "ababc")





                ababc
                    |

*/

class Node {
public:
    vector<Node *> children;
    bool isEndOFWord;

    Node()
    {
        children.resize(26, NULL);
        isEndOFWord = false;
    }
};

class Trie {
private:
    Node *root;
public:
    Trie()
    {
        root = new Node();
    }

    void insertWord(string& word){
        Node* currNode = root;
        for(char ch : word){
            if(currNode->children[(ch - 'a')] == NULL){
                currNode->children[(ch - 'a')] = new Node();
            }
            currNode = currNode->children[(ch - 'a')];
        }
        currNode->isEndOFWord = true;
    }

    int countWays(int index, string& str, vector<int>& dp){
        int ways = 0;
        Node* currNode = root;
        for(int i = index; index < str.size(); i++){
            char ch = str[i];

            if(currNode->children[(ch - 'a')] == NULL){
                return ways;
            }
           
            currNode = currNode->children[(ch - 'a')];
            if(currNode->isEndOFWord){
                ways = (ways + dp[i + 1]) % MOD;
            }
        }
        return ways;
    }
};


int solve(string str, vector<string>& words){
    Trie trie;

    for(auto& word : words){
        trie.insertWord(word);
    }

    int n = str.size(); 
    vector<int> dp(n + 1, 0);

    dp[n] = 1;

    int res = 0;
    for(int i = n-1; i >= 0; i--){
       dp[i] = trie.countWays(i, str, dp);
    }

    return dp[0];
}

void solve()
{
    string word;
    cin >> word;
    int k;
    cin >> k;
    vector<string> words;
    while (k--)
    {
        string w;
        cin >> w;
        words.push_back(w);
    }
    cout << solve(word, words) << endl;
}

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T = 1;
    // cin >> T;
    while (T--)
    {
        solve();
    }
    return 0;
}