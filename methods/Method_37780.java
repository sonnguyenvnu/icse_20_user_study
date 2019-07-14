/** 
 * Converts to primitive array.
 */
public static byte[] values(Byte[] array){
  byte[] dest=new byte[array.length];
  for (int i=0; i < array.length; i++) {
    Byte v=array[i];
    if (v != null) {
      dest[i]=v.byteValue();
    }
  }
  return dest;
}
