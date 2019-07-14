/** 
 * Unsafe version of  {@link #cStencilBits}. 
 */
public static byte ncStencilBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CSTENCILBITS);
}
