/** 
 * Duplicates the specified buffer. <p>This method should be preferred over  {@link DoubleBuffer#duplicate} because it has a much shorter call chain. Long call chains may fail to inline dueto JVM limits, disabling important optimizations (e.g. scalar replacement via Escape Analysis).</p>
 * @param buffer the buffer to duplicate
 * @return the duplicated buffer
 */
public static DoubleBuffer memDuplicate(DoubleBuffer buffer){
  return duplicate(BUFFER_DOUBLE,buffer,PARENT_DOUBLE);
}
