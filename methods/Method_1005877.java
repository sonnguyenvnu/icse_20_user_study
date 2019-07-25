/** 
 * Returns the buffers capacity
 * @return the buffers capacity
 */
public final int capacity(){
  v8.checkThread();
  checkReleased();
  return byteBuffer.capacity();
}
