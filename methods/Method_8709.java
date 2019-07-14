public void changeVideoPreviewState(int state,float progress){
  if (videoPlayer == null) {
    return;
  }
  if (state == 0) {
    startProgressTimer();
    videoPlayer.play();
  }
 else   if (state == 1) {
    stopProgressTimer();
    videoPlayer.pause();
  }
 else   if (state == 2) {
    videoPlayer.seekTo((long)(progress * videoPlayer.getDuration()));
  }
}
