/** 
 * Unsafe version of  {@link #cBlueBits}. 
 */
public static byte ncBlueBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CBLUEBITS);
}
