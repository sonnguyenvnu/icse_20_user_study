private void autoSettle(){
  int sx=getScrollX();
  float dx=mCenterIndex * mIntervalDis - sx - mMaxOverScrollDistance;
  mScroller.startScroll(sx,0,(int)dx,0);
  postInvalidate();
  if (mLastSelectedIndex != mCenterIndex) {
    mLastSelectedIndex=mCenterIndex;
    if (null != mOnWheelItemSelectedListener) {
      mOnWheelItemSelectedListener.onWheelItemSelected(this,mCenterIndex);
    }
  }
}
