/** 
 * Unsafe version of  {@link #cAlphaShift}. 
 */
public static byte ncAlphaShift(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CALPHASHIFT);
}
