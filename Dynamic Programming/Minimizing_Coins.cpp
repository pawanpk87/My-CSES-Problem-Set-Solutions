#include <bits/stdc++.h>
using namespace std;

#define int long long int

int mod = 1000000007;


int solve(int index, int count, int x, vector<int>& coins, vector<vector<int>>& memo){
   if(x == 0){
       return count;
   }

   if(index == -1){
       return INT_MAX;
   }

   if(memo[index][x] != -1){
       return memo[index][x];
   }

   int opt1 = INT_MAX, opt2 = INT_MAX;
   if(coins[index] <= x){
       opt1 = solve(index, count + 1, x - coins[index], coins, memo);
   }
   
   opt2 = solve(index - 1, count, x, coins, memo);

   return memo[index][x] = min(opt1, opt2);
}

int solve(vector<int>& coins, int x){
    
    vector<vector<int>> memo(102, vector<int>(1000002, -1));

    return solve(coins.size() - 1, 0, x, coins, memo);
}

void solve(){
   int n,x;
   cin>>n>>x;

   vector<int> coins;
   for(int i = 0; i < n; i++){
     int num; cin>>num;
     coins.push_back(num);
   }

   int ans = solve(coins, x);

   if(ans == INT_MAX){
    cout<<-1<<endl;
   } else{
    cout<<ans<<endl;
   }
}

int32_t main(){
    int T = 1;
    //cin>>T;
    while(T--){
        solve();
    }
    return 0;
}