/** 
 * Converts to object array.
 */
public static Double[] valuesOf(double[] array){
  Double[] dest=new Double[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Double.valueOf(array[i]);
  }
  return dest;
}
