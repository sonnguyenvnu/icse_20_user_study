/** 
 * Converts to primitive array.
 */
public static boolean[] values(Boolean[] array){
  boolean[] dest=new boolean[array.length];
  for (int i=0; i < array.length; i++) {
    Boolean v=array[i];
    if (v != null) {
      dest[i]=v.booleanValue();
    }
  }
  return dest;
}
