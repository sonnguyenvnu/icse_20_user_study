/** 
 * Converts to object array.
 */
public static Character[] valuesOf(char[] array){
  Character[] dest=new Character[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Character.valueOf(array[i]);
  }
  return dest;
}
