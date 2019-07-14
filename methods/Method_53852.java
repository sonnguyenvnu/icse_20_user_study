/** 
 * Unsafe version of  {@link #mAuthor}. 
 */
public static ByteBuffer nmAuthor(long struct){
  return memByteBufferNT1(memGetAddress(struct + AIImporterDesc.MAUTHOR));
}
