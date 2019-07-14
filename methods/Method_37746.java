/** 
 * Join <code>boolean</code> arrays.
 */
public static boolean[] join(boolean[]... arrays){
  if (arrays.length == 0) {
    return new boolean[0];
  }
  if (arrays.length == 1) {
    return arrays[0];
  }
  int length=0;
  for (  boolean[] array : arrays) {
    length+=array.length;
  }
  boolean[] result=new boolean[length];
  length=0;
  for (  boolean[] array : arrays) {
    System.arraycopy(array,0,result,length,array.length);
    length+=array.length;
  }
  return result;
}
