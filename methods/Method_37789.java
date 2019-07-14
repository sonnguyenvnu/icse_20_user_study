/** 
 * Converts to object array.
 */
public static Long[] valuesOf(long[] array){
  Long[] dest=new Long[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Long.valueOf(array[i]);
  }
  return dest;
}
