/** 
 * check if there is an element that contains the specified string
 * @param str
 * @return  int
 */
public static int contains(String str,String[] arr){
  if (arr == null) {
    return -1;
  }
  for (int i=0; i < arr.length; i++) {
    if (arr[i].contains(str)) {
      return i;
    }
  }
  return -1;
}
