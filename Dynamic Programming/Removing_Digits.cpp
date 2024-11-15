#include <bits/stdc++.h>
using namespace std;

#define int long long int

int mod = 10e7;

int memo[1000001];

    int
    solve(int num)
{
    string strNum = to_string(num);

    if (strNum.size() == 1)
    {
        return 1;
    }
    /**
        Input:
        27

        Output:
        5

        27 -> 20 -> 18 -> 10 -> 9 -> 0


        "2...7"
         |





    */

    if (memo[num] != -1)
    {
        return memo[num];
    }

    int minOperation = INT_MAX;

    for (int i = 0; i < strNum.size(); i++)
    {
        int nextNum = num - (strNum[i] - '0');
        if (nextNum < num)
        {
            minOperation = min(minOperation, 1 + solve(nextNum));
        }
    }

    return memo[num] = minOperation;
}

void solve()
{
    int n;
    cin >> n;
    memset(memo, -1, sizeof(memo));
    cout << solve(n) << endl;
}

int32_t main()
{
    long long T = 1;
    // cin>>T;
    while (T--)
    {
        solve();
    }
    return 0;
}