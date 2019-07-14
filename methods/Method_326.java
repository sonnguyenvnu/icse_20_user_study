/** 
 * ?????? O(N^2)
 * @param nums
 * @param target
 * @return
 */
public int[] getTwo1(int[] nums,int target){
  int[] result=null;
  for (int i=0; i < nums.length; i++) {
    int a=nums[i];
    for (int j=nums.length - 1; j >= 0; j--) {
      int b=nums[j];
      if (i != j && (a + b) == target) {
        result=new int[]{i,j};
      }
    }
  }
  return result;
}
