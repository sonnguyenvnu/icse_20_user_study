private void changeValueByOne(boolean increment){
  mInputText.setVisibility(View.INVISIBLE);
  if (!moveToFinalScrollerPosition(mFlingScroller)) {
    moveToFinalScrollerPosition(mAdjustScroller);
  }
  mPreviousScrollerY=0;
  if (increment) {
    mFlingScroller.startScroll(0,0,0,-mSelectorElementHeight,SNAP_SCROLL_DURATION);
  }
 else {
    mFlingScroller.startScroll(0,0,0,mSelectorElementHeight,SNAP_SCROLL_DURATION);
  }
  invalidate();
}
