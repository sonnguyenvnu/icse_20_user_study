public int removeDuplicates(int[] nums){
  int len=nums.length;
  if (len <= 1)   return len;
  int tail=1;
  for (int i=1; i < len; ++i) {
    if (nums[i - 1] != nums[i]) {
      nums[tail++]=nums[i];
    }
  }
  return tail;
}
