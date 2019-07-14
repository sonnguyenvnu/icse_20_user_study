/** 
 * Unsafe version of  {@link #description}. 
 */
public static ByteBuffer ndescription(long struct){
  return memByteBufferNT1(memGetAddress(struct + AIExportFormatDesc.DESCRIPTION));
}
