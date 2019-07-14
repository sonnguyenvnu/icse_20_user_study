/** 
 * Unsafe version of  {@link #cColorBits(byte) cColorBits}. 
 */
public static void ncColorBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CCOLORBITS,value);
}
