/** 
 * Starts displaying all views in order of their insertion in the queue, one after another
 */
public void show(){
  if (!mQueue.isEmpty()) {
    mCurrent=mQueue.poll();
    mCurrentOriginalDismissListener=mCurrent.getDismissListener();
    mCurrent.setDismissListener(this);
    mCurrent.show();
  }
 else   if (mCompleteListener != null) {
    mCompleteListener.onComplete();
  }
}
