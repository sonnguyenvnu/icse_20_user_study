public void smoothSelectIndex(int index){
  if (!mScroller.isFinished()) {
    mScroller.abortAnimation();
  }
  int deltaIndex=index - mCenterIndex;
  mScroller.startScroll(getScrollX(),0,(int)(deltaIndex * mIntervalDis),0);
  invalidate();
}
