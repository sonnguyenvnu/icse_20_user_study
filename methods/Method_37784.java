/** 
 * Converts to primitive array.
 */
public static short[] values(Short[] array){
  short[] dest=new short[array.length];
  for (int i=0; i < array.length; i++) {
    Short v=array[i];
    if (v != null) {
      dest[i]=v.shortValue();
    }
  }
  return dest;
}
