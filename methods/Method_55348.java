/** 
 * Unsafe version of  {@link #cDepthBits}. 
 */
public static byte ncDepthBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CDEPTHBITS);
}
