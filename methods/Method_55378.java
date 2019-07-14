/** 
 * Unsafe version of  {@link #bReserved(byte) bReserved}. 
 */
public static void nbReserved(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.BRESERVED,value);
}
