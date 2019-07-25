/** 
 * ????
 */
public void reset(){
  if (mAnimator != null) {
    mAnimator.cancel();
  }
  mBaseDataList.clear();
  mDataList.clear();
  invalidate();
}
