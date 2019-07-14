/** 
 * Unsafe version of  {@link #fileExtension}. 
 */
public static ByteBuffer nfileExtension(long struct){
  return memByteBufferNT1(memGetAddress(struct + AIExportFormatDesc.FILEEXTENSION));
}
