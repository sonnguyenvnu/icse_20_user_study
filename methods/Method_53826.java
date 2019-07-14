/** 
 * Unsafe version of  {@link #id}. 
 */
public static ByteBuffer nid(long struct){
  return memByteBufferNT1(memGetAddress(struct + AIExportFormatDesc.ID));
}
