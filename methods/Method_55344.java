/** 
 * Unsafe version of  {@link #cAccumRedBits}. 
 */
public static byte ncAccumRedBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMREDBITS);
}
