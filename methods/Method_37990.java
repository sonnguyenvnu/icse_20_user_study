/** 
 * Finds the very last index of a substring from the specified array. It returns an int[2] where int[0] represents the substring index and int[1] represents position where substring was found. Returns <code>null</code> if noting found.
 * @param s         source string
 * @param arr       string array
 * @param fromIndex starting position
 */
public static int[] lastIndexOfIgnoreCase(final String s,final String[] arr,final int fromIndex){
  int arrLen=arr.length;
  int index=-1;
  int last=-1;
  for (int j=0; j < arrLen; j++) {
    int i=lastIndexOfIgnoreCase(s,arr[j],fromIndex);
    if (i != -1) {
      if (i > index) {
        index=i;
        last=j;
      }
    }
  }
  return last == -1 ? null : new int[]{last,index};
}
