/** 
 * Unsafe version of  {@link #cAuxBuffers}. 
 */
public static byte ncAuxBuffers(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.CAUXBUFFERS);
}
