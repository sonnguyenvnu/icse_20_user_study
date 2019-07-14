private void releasePlayer(boolean onClose){
  if (videoPlayer != null) {
    AndroidUtilities.cancelRunOnUIThread(setLoadingRunnable);
    videoPlayer.releasePlayer(true);
    videoPlayer=null;
    updateAccessibilityOverlayVisibility();
  }
  toggleMiniProgress(false,false);
  pipAvailable=false;
  playerInjected=false;
  if (pipItem.isEnabled()) {
    pipItem.setEnabled(false);
    pipItem.setAlpha(0.5f);
  }
  if (keepScreenOnFlagSet) {
    try {
      parentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      keepScreenOnFlagSet=false;
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  if (aspectRatioFrameLayout != null) {
    try {
      containerView.removeView(aspectRatioFrameLayout);
    }
 catch (    Throwable ignore) {
    }
    aspectRatioFrameLayout=null;
  }
  if (videoTextureView != null) {
    videoTextureView=null;
  }
  if (isPlaying) {
    isPlaying=false;
    if (!onClose) {
      videoPlayButton.setImageResource(R.drawable.inline_video_play);
    }
    AndroidUtilities.cancelRunOnUIThread(updateProgressRunnable);
  }
  if (!onClose && !inPreview && !requestingPreview) {
    videoPlayerControlFrameLayout.setVisibility(View.GONE);
    dateTextView.setVisibility(View.VISIBLE);
    nameTextView.setVisibility(View.VISIBLE);
    if (allowShare) {
      shareButton.setVisibility(View.VISIBLE);
      menuItem.hideSubItem(gallery_menu_share);
    }
  }
}
