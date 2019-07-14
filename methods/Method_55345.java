/** 
 * Unsafe version of  {@link #cAccumGreenBits}. 
 */
public static byte ncAccumGreenBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMGREENBITS);
}
