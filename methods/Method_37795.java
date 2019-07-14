/** 
 * Converts to object array.
 */
public static Boolean[] valuesOf(boolean[] array){
  Boolean[] dest=new Boolean[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Boolean.valueOf(array[i]);
  }
  return dest;
}
