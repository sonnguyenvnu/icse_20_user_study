/** 
 * Join <code>String</code> arrays.
 */
public static String[] join(String[]... arrays){
  if (arrays.length == 0) {
    return new String[0];
  }
  if (arrays.length == 1) {
    return arrays[0];
  }
  int length=0;
  for (  String[] array : arrays) {
    length+=array.length;
  }
  String[] result=new String[length];
  length=0;
  for (  String[] array : arrays) {
    System.arraycopy(array,0,result,length,array.length);
    length+=array.length;
  }
  return result;
}
