/** 
 * Unsafe version of  {@link #cRedBits}. 
 */
public static byte ncRedBits(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CREDBITS);
}
