public double findMedianSortedArrays(int[] nums1,int[] nums2){
  int len=nums1.length + nums2.length;
  if (len % 2 == 0) {
    return (helper(nums1,0,nums2,0,len / 2) + helper(nums1,0,nums2,0,len / 2 + 1)) / 2.0;
  }
  return helper(nums1,0,nums2,0,(len + 1) / 2);
}
