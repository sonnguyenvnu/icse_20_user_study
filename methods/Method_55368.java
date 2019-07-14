/** 
 * Unsafe version of  {@link #cAlphaShift(byte) cAlphaShift}. 
 */
public static void ncAlphaShift(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CALPHASHIFT,value);
}
