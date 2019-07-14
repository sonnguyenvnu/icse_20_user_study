/** 
 * Unsafe version of  {@link #mComments}. 
 */
public static ByteBuffer nmComments(long struct){
  return memByteBufferNT1(memGetAddress(struct + AIImporterDesc.MCOMMENTS));
}
