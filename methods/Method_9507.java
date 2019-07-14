public void injectVideoPlayerToMediaController(){
  if (videoPlayer.isPlaying()) {
    MediaController.getInstance().injectVideoPlayer(videoPlayer,currentMessageObject);
    videoPlayer=null;
    updateAccessibilityOverlayVisibility();
  }
}
