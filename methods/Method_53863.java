/** 
 * Unsafe version of  {@link #mFileExtensions}. 
 */
public static ByteBuffer nmFileExtensions(long struct){
  return memByteBufferNT1(memGetAddress(struct + AIImporterDesc.MFILEEXTENSIONS));
}
