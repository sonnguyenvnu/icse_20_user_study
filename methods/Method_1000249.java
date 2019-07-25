private int backtrack(int[] nums,int[] dp,int sum){
  int total=0;
  if (sum < 0)   return 0;
  if (dp[sum] != -1)   return dp[sum];
 else {
    for (    int num : nums) {
      total+=backtrack(nums,dp,sum - num);
    }
  }
  dp[sum]=total;
  return dp[sum];
}
