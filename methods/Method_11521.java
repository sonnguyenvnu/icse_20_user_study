private int computePanelTopPosition(float slideOffset){
  int slidingViewHeight=mSlideableView != null ? mSlideableView.getMeasuredHeight() : 0;
  int slidePixelOffset=(int)(slideOffset * mSlideRange);
  return mIsSlidingUp ? getMeasuredHeight() - getPaddingBottom() - mPanelHeight - slidePixelOffset : getPaddingTop() - slidingViewHeight + mPanelHeight + slidePixelOffset;
}
