/** 
 * Duplicates the specified buffer. <p>This method should be preferred over  {@link IntBuffer#duplicate} because it has a much shorter call chain. Long call chains may fail to inline dueto JVM limits, disabling important optimizations (e.g. scalar replacement via Escape Analysis).</p>
 * @param buffer the buffer to duplicate
 * @return the duplicated buffer
 */
public static IntBuffer memDuplicate(IntBuffer buffer){
  return duplicate(BUFFER_INT,buffer,PARENT_INT);
}
