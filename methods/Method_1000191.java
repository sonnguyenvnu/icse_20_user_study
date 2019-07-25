@Override public void run(){
  if (null == mData || mData.size() == 0)   return;
  if (mScroller.isFinished() && !isForceFinishScroll) {
    if (mItemHeight == 0)     return;
    int position=(-mScrollOffsetY / mItemHeight + mSelectedItemPosition) % mData.size();
    position=position < 0 ? position + mData.size() : position;
    if (isDebug)     Log.i(TAG,position + ":" + mData.get(position) + ":" + mScrollOffsetY);
    mCurrentItemPosition=position;
    if (null != mOnItemSelectedListener && isTouchTriggered)     mOnItemSelectedListener.onItemSelected(this,mData.get(position),position);
    if (null != mOnWheelChangeListener && isTouchTriggered) {
      mOnWheelChangeListener.onWheelSelected(position);
      mOnWheelChangeListener.onWheelScrollStateChanged(SCROLL_STATE_IDLE);
    }
  }
  if (mScroller.computeScrollOffset()) {
    if (null != mOnWheelChangeListener)     mOnWheelChangeListener.onWheelScrollStateChanged(SCROLL_STATE_SCROLLING);
    mScrollOffsetY=mScroller.getCurrY();
    postInvalidate();
    mHandler.postDelayed(this,16);
  }
}
