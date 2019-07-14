private boolean ensureScrollWheelAdjusted(){
  int deltaY=mInitialScrollOffset - mCurrentScrollOffset;
  if (deltaY != 0) {
    mPreviousScrollerY=0;
    if (Math.abs(deltaY) > mSelectorElementHeight / 2) {
      deltaY+=(deltaY > 0) ? -mSelectorElementHeight : mSelectorElementHeight;
    }
    mAdjustScroller.startScroll(0,0,0,deltaY,SELECTOR_ADJUSTMENT_DURATION_MILLIS);
    invalidate();
    return true;
  }
  return false;
}
