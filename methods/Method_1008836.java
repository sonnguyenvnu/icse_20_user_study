/** 
 * Returns  {@code true} if {@code target} is present as an element anywhere in {@code array}.
 * @param array an array of {@code int} values, possibly empty
 * @param target a primitive {@code int} value
 * @return {@code true} if {@code array[i] == target} for some value of {@code i}
 */
public static boolean contains(int[] array,int target){
  for (  int value : array) {
    if (value == target) {
      return true;
    }
  }
  return false;
}
