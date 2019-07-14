public boolean increasingTriplet(int[] nums){
  int firstMin=Integer.MAX_VALUE;
  int secondMin=Integer.MAX_VALUE;
  for (  int n : nums) {
    if (n <= firstMin) {
      firstMin=n;
    }
 else     if (n < secondMin) {
      secondMin=n;
    }
 else     if (n > secondMin) {
      return true;
    }
  }
  return false;
}
