/** 
 * Converts to primitive array.
 */
public static long[] values(Long[] array){
  long[] dest=new long[array.length];
  for (int i=0; i < array.length; i++) {
    Long v=array[i];
    if (v != null) {
      dest[i]=v.longValue();
    }
  }
  return dest;
}
