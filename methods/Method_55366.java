/** 
 * Unsafe version of  {@link #cBlueShift(byte) cBlueShift}. 
 */
public static void ncBlueShift(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CBLUESHIFT,value);
}
