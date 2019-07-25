/** 
 * Returns the number of times a value occurs in a given array. 
 */
public static double count(int[] sampled,int val){
  int count=0;
  for (int i=0; i < sampled.length; i++) {
    if (sampled[i] == val) {
      count++;
    }
  }
  return count;
}
