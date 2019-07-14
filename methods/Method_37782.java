/** 
 * Converts to primitive array.
 */
public static char[] values(Character[] array){
  char[] dest=new char[array.length];
  for (int i=0; i < array.length; i++) {
    Character v=array[i];
    if (v != null) {
      dest[i]=v.charValue();
    }
  }
  return dest;
}
