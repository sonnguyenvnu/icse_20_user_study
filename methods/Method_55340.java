/** 
 * Unsafe version of  {@link #cBlueShift}. 
 */
public static byte ncBlueShift(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CBLUESHIFT);
}
