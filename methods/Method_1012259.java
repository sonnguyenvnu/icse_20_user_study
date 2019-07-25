/** 
 * Cancels the queue
 * @param hideCurrent hides current GuideCaseView
 */
public void cancel(boolean hideCurrent){
  if (hideCurrent && mCurrent != null) {
    mCurrent.hide();
  }
  if (!mQueue.isEmpty()) {
    mQueue.clear();
  }
}
