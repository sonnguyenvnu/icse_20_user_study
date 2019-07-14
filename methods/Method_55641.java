/** 
 * Duplicates the specified buffer. The returned buffer will have the same  {@link ByteOrder} as the source buffer.<p>This method should be preferred over  {@link ByteBuffer#duplicate} because it has a much shorter call chain. Long call chains may fail to inline dueto JVM limits, disabling important optimizations (e.g. scalar replacement via Escape Analysis).</p>
 * @param buffer the buffer to duplicate
 * @return the duplicated buffer
 */
public static ByteBuffer memDuplicate(ByteBuffer buffer){
  return duplicate(BUFFER_BYTE,buffer,PARENT_BYTE).order(buffer.order());
}
