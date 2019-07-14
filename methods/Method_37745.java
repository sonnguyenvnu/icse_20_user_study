/** 
 * Join <code>double</code> arrays.
 */
public static double[] join(double[]... arrays){
  if (arrays.length == 0) {
    return new double[0];
  }
  if (arrays.length == 1) {
    return arrays[0];
  }
  int length=0;
  for (  double[] array : arrays) {
    length+=array.length;
  }
  double[] result=new double[length];
  length=0;
  for (  double[] array : arrays) {
    System.arraycopy(array,0,result,length,array.length);
    length+=array.length;
  }
  return result;
}
