#include <bits/stdc++.h>
#include <cstring>
using namespace std;

#define int long long int

const int mod = 1000000007;

int dp[5001][5001];

int minDistance(string word1, string word2)
{
    int n = word1.length();
    int m = word2.length();

    for (int j = 1; j <= m; j++)
    {
        dp[0][j] = j;
    }
    for (int i = 0; i <= n; i++)
    {
        dp[i][0] = i;
    }

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            char ch1 = word1[i - 1];
            char ch2 = word2[j - 1];
            if (ch1 == ch2)
            {
                dp[i][j] = dp[i - 1][j - 1];
            }
            else
            {
                dp[i][j] = 1 + min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]});
            }
        }
    }

    return dp[n][m];
}

void solve()
{
    // int n;
    // cin >> n;

    string str1, str2; cin>>str1>>str2;

    cout << minDistance(str1, str2) << endl;
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
