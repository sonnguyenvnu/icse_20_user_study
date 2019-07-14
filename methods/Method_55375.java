/** 
 * Unsafe version of  {@link #cStencilBits(byte) cStencilBits}. 
 */
public static void ncStencilBits(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CSTENCILBITS,value);
}
