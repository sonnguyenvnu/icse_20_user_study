/** 
 * Unsafe version of  {@link #cAccumAlphaBits}. 
 */
public static byte ncAccumAlphaBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMALPHABITS);
}
