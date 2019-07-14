/** 
 * Unsafe version of  {@link #cGreenShift}. 
 */
public static byte ncGreenShift(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CGREENSHIFT);
}
