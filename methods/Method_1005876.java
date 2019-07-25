/** 
 * Returns the buffers limit
 * @return the buffers limit
 */
public int limit(){
  v8.checkThread();
  checkReleased();
  return byteBuffer.limit();
}
