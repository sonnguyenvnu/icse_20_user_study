/** 
 * Unsafe version of  {@link #cAccumGreenBits(byte) cAccumGreenBits}. 
 */
public static void ncAccumGreenBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMGREENBITS,value);
}
