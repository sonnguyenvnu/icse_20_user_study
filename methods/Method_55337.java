/** 
 * Unsafe version of  {@link #cGreenBits}. 
 */
public static byte ncGreenBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CGREENBITS);
}
