/** 
 * Unsafe version of  {@link #cRedBits(byte) cRedBits}. 
 */
public static void ncRedBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CREDBITS,value);
}
