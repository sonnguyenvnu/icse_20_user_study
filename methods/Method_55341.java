/** 
 * Unsafe version of  {@link #cAlphaBits}. 
 */
public static byte ncAlphaBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CALPHABITS);
}
