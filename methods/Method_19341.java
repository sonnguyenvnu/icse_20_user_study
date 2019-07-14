@VisibleForTesting void onNewVisibleRange(int firstVisiblePosition,int lastVisiblePosition){
  mCurrentFirstVisiblePosition=firstVisiblePosition;
  mCurrentLastVisiblePosition=lastVisiblePosition;
  mViewportManager.resetShouldUpdate();
  maybePostUpdateViewportAndComputeRange();
}
