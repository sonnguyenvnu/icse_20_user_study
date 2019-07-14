/** 
 * Join <code>float</code> arrays.
 */
public static float[] join(float[]... arrays){
  if (arrays.length == 0) {
    return new float[0];
  }
  if (arrays.length == 1) {
    return arrays[0];
  }
  int length=0;
  for (  float[] array : arrays) {
    length+=array.length;
  }
  float[] result=new float[length];
  length=0;
  for (  float[] array : arrays) {
    System.arraycopy(array,0,result,length,array.length);
    length+=array.length;
  }
  return result;
}
