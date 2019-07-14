@Override public boolean onSingleTapConfirmed(MotionEvent e){
  if (discardTap) {
    return false;
  }
  boolean drawTextureView=aspectRatioFrameLayout != null && aspectRatioFrameLayout.getVisibility() == View.VISIBLE;
  if (radialProgressViews[0] != null && photoContainerView != null && !drawTextureView) {
    int state=radialProgressViews[0].backgroundState;
    if (state > 0 && state <= 3) {
      float x=e.getX();
      float y=e.getY();
      if (x >= (getContainerViewWidth() - AndroidUtilities.dp(100)) / 2.0f && x <= (getContainerViewWidth() + AndroidUtilities.dp(100)) / 2.0f && y >= (getContainerViewHeight() - AndroidUtilities.dp(100)) / 2.0f && y <= (getContainerViewHeight() + AndroidUtilities.dp(100)) / 2.0f) {
        onActionClick(true);
        checkProgress(0,true);
        return true;
      }
    }
  }
  toggleActionBar(!isActionBarVisible,true);
  return true;
}
