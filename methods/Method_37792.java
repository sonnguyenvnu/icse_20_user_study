/** 
 * Converts to primitive array.
 */
public static double[] values(Double[] array){
  double[] dest=new double[array.length];
  for (int i=0; i < array.length; i++) {
    Double v=array[i];
    if (v != null) {
      dest[i]=v.doubleValue();
    }
  }
  return dest;
}
