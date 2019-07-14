@Override public boolean releaseAction(){
  boolean isOnRefresh=false;
  int height=getVisiableHeight();
  if (height == 0)   isOnRefresh=false;
  if (getVisiableHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
    setState(STATE_REFRESHING);
    isOnRefresh=true;
  }
  if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
  }
  int destHeight=0;
  if (mState == STATE_REFRESHING) {
    destHeight=mMeasuredHeight;
  }
  smoothScrollTo(destHeight);
  return isOnRefresh;
}
