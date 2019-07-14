private void onPanelDragged(int newTop){
  if (mSlideState != PanelState.DRAGGING) {
    mLastNotDraggingSlideState=mSlideState;
  }
  setPanelStateInternal(PanelState.DRAGGING);
  mSlideOffset=computeSlideOffset(newTop);
  applyParallaxForCurrentSlideOffset();
  dispatchOnPanelSlide(mSlideableView);
  LayoutParams lp=(LayoutParams)mMainView.getLayoutParams();
  int defaultHeight=getHeight() - getPaddingBottom() - getPaddingTop() - mPanelHeight;
  if (mSlideOffset <= 0 && !mOverlayContent) {
    lp.height=mIsSlidingUp ? (newTop - getPaddingBottom()) : (getHeight() - getPaddingBottom() - mSlideableView.getMeasuredHeight() - newTop);
    if (lp.height == defaultHeight) {
      lp.height=LayoutParams.MATCH_PARENT;
    }
    mMainView.requestLayout();
  }
 else   if (lp.height != LayoutParams.MATCH_PARENT && !mOverlayContent) {
    lp.height=LayoutParams.MATCH_PARENT;
    mMainView.requestLayout();
  }
}
