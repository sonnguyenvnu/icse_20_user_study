/** 
 * Unsafe version of  {@link #cGreenShift(byte) cGreenShift}. 
 */
public static void ncGreenShift(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CGREENSHIFT,value);
}
