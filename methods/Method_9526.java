@Override public boolean onSingleTapConfirmed(MotionEvent e){
  if (discardTap) {
    return false;
  }
  if (containerView.getTag() != null) {
    boolean drawTextureView=aspectRatioFrameLayout != null && aspectRatioFrameLayout.getVisibility() == View.VISIBLE;
    float x=e.getX();
    float y=e.getY();
    if (sharedMediaType == DataQuery.MEDIA_FILE && currentMessageObject != null) {
      if (!currentMessageObject.canPreviewDocument()) {
        float vy=(getContainerViewHeight() - AndroidUtilities.dp(360)) / 2.0f;
        if (y >= vy && y <= vy + AndroidUtilities.dp(360)) {
          onActionClick(true);
          return true;
        }
      }
    }
 else {
      if (photoProgressViews[0] != null && containerView != null && !drawTextureView) {
        int state=photoProgressViews[0].backgroundState;
        if (state > 0 && state <= 3) {
          if (x >= (getContainerViewWidth() - AndroidUtilities.dp(100)) / 2.0f && x <= (getContainerViewWidth() + AndroidUtilities.dp(100)) / 2.0f && y >= (getContainerViewHeight() - AndroidUtilities.dp(100)) / 2.0f && y <= (getContainerViewHeight() + AndroidUtilities.dp(100)) / 2.0f) {
            onActionClick(true);
            checkProgress(0,true);
            return true;
          }
        }
      }
    }
    toggleActionBar(!isActionBarVisible,true);
  }
 else   if (sendPhotoType == 0 || sendPhotoType == 4) {
    if (isCurrentVideo) {
      videoPlayButton.callOnClick();
    }
 else {
      checkImageView.performClick();
    }
  }
 else   if (currentBotInlineResult != null && (currentBotInlineResult.type.equals("video") || MessageObject.isVideoDocument(currentBotInlineResult.document))) {
    int state=photoProgressViews[0].backgroundState;
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
 else   if (sendPhotoType == 2) {
    if (isCurrentVideo) {
      videoPlayButton.callOnClick();
    }
  }
  return true;
}
