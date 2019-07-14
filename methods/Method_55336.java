/** 
 * Unsafe version of  {@link #cRedShift}. 
 */
public static byte ncRedShift(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CREDSHIFT);
}
