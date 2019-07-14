/** 
 * Converts to object array.
 */
public static Short[] valuesOf(short[] array){
  Short[] dest=new Short[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Short.valueOf(array[i]);
  }
  return dest;
}
