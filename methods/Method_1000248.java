public int change(int amount,int[] coins){
  int[][] dp=new int[coins.length][amount + 1];
  for (int i=0, l=coins.length; i < l; i++)   Arrays.fill(dp[i],-1);
  return dp(dp,0,coins,coins.length,amount);
}
