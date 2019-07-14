/** 
 * Converts to object array.
 */
public static Integer[] valuesOf(int[] array){
  Integer[] dest=new Integer[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Integer.valueOf(array[i]);
  }
  return dest;
}
