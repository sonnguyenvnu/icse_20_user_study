/** 
 * Unsafe version of  {@link #cAccumBits(byte) cAccumBits}. 
 */
public static void ncAccumBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMBITS,value);
}
