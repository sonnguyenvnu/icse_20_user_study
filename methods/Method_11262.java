private void refreshCenter(int offsetX){
  int offset=(int)(offsetX + mMaxOverScrollDistance);
  int tempIndex=Math.round(offset / mIntervalDis);
  tempIndex=safeCenter(tempIndex);
  if (mCenterIndex == tempIndex) {
    return;
  }
  mCenterIndex=tempIndex;
  if (null != mOnWheelItemSelectedListener) {
    mOnWheelItemSelectedListener.onWheelItemChanged(this,mCenterIndex);
  }
}
