/** 
 * Unsafe version of  {@link #cAccumRedBits(byte) cAccumRedBits}. 
 */
public static void ncAccumRedBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMREDBITS,value);
}
