/** 
 * Join <code>short</code> arrays.
 */
public static short[] join(short[]... arrays){
  if (arrays.length == 0) {
    return new short[0];
  }
  if (arrays.length == 1) {
    return arrays[0];
  }
  int length=0;
  for (  short[] array : arrays) {
    length+=array.length;
  }
  short[] result=new short[length];
  length=0;
  for (  short[] array : arrays) {
    System.arraycopy(array,0,result,length,array.length);
    length+=array.length;
  }
  return result;
}
