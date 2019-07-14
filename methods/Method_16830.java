private int helper(int[] nums,int left,int right){
  if (left >= right)   return nums[left];
  int mid=(left + right) >> 1;
  int leftAns=helper(nums,left,mid);
  int rightAns=helper(nums,mid + 1,right);
  int leftMax=nums[mid], rightMax=nums[mid + 1];
  int temp=0;
  for (int i=mid; i >= left; --i) {
    temp+=nums[i];
    if (temp > leftMax)     leftMax=temp;
  }
  temp=0;
  for (int i=mid + 1; i <= right; ++i) {
    temp+=nums[i];
    if (temp > rightMax)     rightMax=temp;
  }
  return Math.max(Math.max(leftAns,rightAns),leftMax + rightMax);
}
