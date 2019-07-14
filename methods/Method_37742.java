/** 
 * Join <code>char</code> arrays.
 */
public static char[] join(char[]... arrays){
  if (arrays.length == 0) {
    return new char[0];
  }
  if (arrays.length == 1) {
    return arrays[0];
  }
  int length=0;
  for (  char[] array : arrays) {
    length+=array.length;
  }
  char[] result=new char[length];
  length=0;
  for (  char[] array : arrays) {
    System.arraycopy(array,0,result,length,array.length);
    length+=array.length;
  }
  return result;
}
