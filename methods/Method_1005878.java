/** 
 * @return
 */
public final int position(){
  v8.checkThread();
  checkReleased();
  return byteBuffer.position();
}
