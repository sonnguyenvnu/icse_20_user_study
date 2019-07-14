/** 
 * Converts to object array.
 */
public static Byte[] valuesOf(byte[] array){
  Byte[] dest=new Byte[array.length];
  for (int i=0; i < array.length; i++) {
    dest[i]=Byte.valueOf(array[i]);
  }
  return dest;
}
