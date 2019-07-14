/** 
 * Unsafe version of  {@link #cBlueBits(byte) cBlueBits}. 
 */
public static void ncBlueBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CBLUEBITS,value);
}
