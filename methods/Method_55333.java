/** 
 * Unsafe version of  {@link #iPixelType}. 
 */
public static byte niPixelType(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.IPIXELTYPE);
}
