#include <bits/stdc++.h>
using namespace std;

#define int long long int

int mod = 1000000007;
 
int uniquePathsWithObstacles(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();
 
    if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
    {
        return 0;
    }
 
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                grid[i][j] = -1;
            }
        }
    }
 
    for (int j = 0; j < m; j++)
    {
        if (grid[0][j] == -1)
        {
            break;
        }
        grid[0][j] = 1;
    }
 
    for (int i = 0; i < n; i++)
    {
        if (grid[i][0] == -1)
        {
            break;
        }
        grid[i][0] = 1;
    }
 
    for (int i = 1; i < n; i++)
    {
        for (int j = 1; j < m; j++)
        {
            if (grid[i][j] == 0)
            {
                if (grid[i - 1][j] == -1 || grid[i][j - 1] == -1)
                {
                    grid[i][j] = max(grid[i - 1][j], grid[i][j - 1]);
                }
                else
                {
                    grid[i][j] = (grid[i - 1][j] + grid[i][j - 1]) % mod;
                }
            }
        }
    }
 
    return grid[n - 1][m - 1] > 0 ? grid[n - 1][m - 1] % mod : 0;
}
 
void solve()
{
    int n;
    cin >> n;
 
    vector<vector<int>> grid(n, vector<int>(n, 0));
 
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            char ch;
            cin >> ch;
            if (ch == '*')
            {
                grid[i][j] = 1;
            }
            else
            {
                grid[i][j] = 0;
            }
        }
    }
 
    cout<<uniquePathsWithObstacles(grid)<<endl;
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