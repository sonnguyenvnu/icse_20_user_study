/** 
 * Unsafe version of  {@link #cAccumBlueBits(byte) cAccumBlueBits}. 
 */
public static void ncAccumBlueBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CACCUMBLUEBITS,value);
}
