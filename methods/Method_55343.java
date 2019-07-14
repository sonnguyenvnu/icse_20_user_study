/** 
 * Unsafe version of  {@link #cAccumBits}. 
 */
public static byte ncAccumBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMBITS);
}
