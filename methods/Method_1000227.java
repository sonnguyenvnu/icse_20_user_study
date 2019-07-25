private void replace(int i,int[] nums){
  if (i > 0 && i <= L && i != nums[i - 1]) {
    int v=nums[i - 1];
    nums[i - 1]=i;
    replace(v,nums);
  }
}
