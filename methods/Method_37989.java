/** 
 * Finds the very first index of a substring from the specified array. It returns an int[2] where int[0] represents the substring index and int[1] represents position where substring was found. Returns <code>null</code> if noting found.
 * @param s      source string
 * @param arr    string array
 * @param start  starting position
 */
public static int[] indexOfIgnoreCase(final String s,final String[] arr,final int start){
  int arrLen=arr.length;
  int index=Integer.MAX_VALUE;
  int last=-1;
  for (int j=0; j < arrLen; j++) {
    int i=indexOfIgnoreCase(s,arr[j],start);
    if (i != -1) {
      if (i < index) {
        index=i;
        last=j;
      }
    }
  }
  return last == -1 ? null : new int[]{last,index};
}
