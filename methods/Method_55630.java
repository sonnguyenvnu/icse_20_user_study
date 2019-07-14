/** 
 * Returns the memory address of the specified buffer. [INTERNAL USE ONLY]
 * @param buffer the buffer
 * @return the memory address
 */
public static long memAddress0(Buffer buffer){
  return UNSAFE.getLong(buffer,ADDRESS);
}
