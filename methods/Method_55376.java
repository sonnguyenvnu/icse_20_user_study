/** 
 * Unsafe version of  {@link #cAuxBuffers(byte) cAuxBuffers}. 
 */
public static void ncAuxBuffers(long struct,byte value){
  UNSAFE.putByte(null,struct + PIXELFORMATDESCRIPTOR.CAUXBUFFERS,value);
}
