/** 
 * Unsafe version of  {@link #cAlphaBits(byte) cAlphaBits}. 
 */
public static void ncAlphaBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CALPHABITS,value);
}
