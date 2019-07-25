/** 
 * @since 6.1.0
 */
public void release(){
  if (mSemaphore == null) {
    return;
  }
  mSemaphore.release();
}
