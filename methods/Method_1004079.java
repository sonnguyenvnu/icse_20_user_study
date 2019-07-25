/** 
 * Returns a modified version of the array with all string slots normalized. It is up to the implementation to replace strings in the array instance or return a new array instance.
 * @param arr String array or <code>null</code>
 * @return normalized instance or <code>null</code>
 */
public String[] get(final String[] arr){
  if (arr == null) {
    return null;
  }
  if (arr.length == 0) {
    return EMPTY_ARRAY;
  }
  for (int i=0; i < arr.length; i++) {
    arr[i]=get(arr[i]);
  }
  return arr;
}
