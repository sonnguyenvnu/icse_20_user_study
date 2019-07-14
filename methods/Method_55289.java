/** 
 * Unsafe version of  {@link #dmFormName}. 
 */
public static ByteBuffer ndmFormName(long struct){
  return memByteBuffer(struct + DEVMODE.DMFORMNAME,32 * 2);
}
