/** 
 * Unsafe version of  {@link #iPixelType(byte) iPixelType}. 
 */
public static void niPixelType(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.IPIXELTYPE,value);
}
