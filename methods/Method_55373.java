/** 
 * Unsafe version of  {@link #cAccumAlphaBits(byte) cAccumAlphaBits}. 
 */
public static void ncAccumAlphaBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMALPHABITS,value);
}
