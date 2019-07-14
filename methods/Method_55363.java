/** 
 * Unsafe version of  {@link #cGreenBits(byte) cGreenBits}. 
 */
public static void ncGreenBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CGREENBITS,value);
}
