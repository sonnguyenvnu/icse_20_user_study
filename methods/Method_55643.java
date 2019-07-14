/** 
 * Duplicates the specified buffer. <p>This method should be preferred over  {@link LongBuffer#duplicate} because it has a much shorter call chain. Long call chains may fail to inline dueto JVM limits, disabling important optimizations (e.g. scalar replacement via Escape Analysis).</p>
 * @param buffer the buffer to duplicate
 * @return the duplicated buffer
 */
public static LongBuffer memDuplicate(LongBuffer buffer){
  return duplicate(BUFFER_LONG,buffer,PARENT_LONG);
}
