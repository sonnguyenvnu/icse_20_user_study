/** 
 * Converts to primitive array.
 */
public static int[] values(Integer[] array){
  int[] dest=new int[array.length];
  for (int i=0; i < array.length; i++) {
    Integer v=array[i];
    if (v != null) {
      dest[i]=v.intValue();
    }
  }
  return dest;
}
