private void releasePlayer(){
  if (videoPlayer != null) {
    videoPlayer.releasePlayer(true);
    videoPlayer=null;
  }
  try {
    parentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  if (aspectRatioFrameLayout != null) {
    photoContainerView.removeView(aspectRatioFrameLayout);
    aspectRatioFrameLayout=null;
  }
  if (videoTextureView != null) {
    videoTextureView=null;
  }
  if (isPlaying) {
    isPlaying=false;
    videoPlayButton.setImageResource(R.drawable.inline_video_play);
    AndroidUtilities.cancelRunOnUIThread(updateProgressRunnable);
  }
  bottomLayout.setVisibility(View.GONE);
}
