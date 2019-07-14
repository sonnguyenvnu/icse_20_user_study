public int lengthOfLIS(int[] nums){
  if (nums == null || nums.length < 1) {
    return 0;
  }
  int[] dp=new int[nums.length];
  dp[0]=1;
  int max=1;
  for (int i=1; i < dp.length; i++) {
    int currentMax=0;
    for (int j=0; j < i; j++) {
      if (nums[i] > nums[j]) {
        currentMax=Math.max(currentMax,dp[j]);
      }
    }
    dp[i]=1 + currentMax;
    max=Math.max(max,dp[i]);
  }
  return max;
}
