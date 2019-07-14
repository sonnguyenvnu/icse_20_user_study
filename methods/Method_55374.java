/** 
 * Unsafe version of  {@link #cDepthBits(byte) cDepthBits}. 
 */
public static void ncDepthBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CDEPTHBITS,value);
}
