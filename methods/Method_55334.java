/** 
 * Unsafe version of  {@link #cColorBits}. 
 */
public static byte ncColorBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CCOLORBITS);
}
