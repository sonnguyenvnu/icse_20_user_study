/** 
 * Unsafe version of  {@link #cAccumBlueBits}. 
 */
public static byte ncAccumBlueBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMBLUEBITS);
}
