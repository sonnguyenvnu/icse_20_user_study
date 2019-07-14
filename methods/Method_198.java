public int minSubArrayLen(int s,int[] nums){
  if (nums == null || nums.length == 0) {
    return 0;
  }
  int i=0;
  int j=0;
  int result=Integer.MAX_VALUE;
  int total=0;
  while (i < nums.length) {
    total+=nums[i++];
    while (total >= s) {
      result=Math.min(result,i - j);
      total-=nums[j++];
    }
  }
  return result == Integer.MAX_VALUE ? 0 : result;
}
