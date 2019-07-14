private void preparePlayer(){
  if (playVideoUrl == null) {
    return;
  }
  if (playVideoUrl != null && playAudioUrl != null) {
    videoPlayer.preparePlayerLoop(Uri.parse(playVideoUrl),playVideoType,Uri.parse(playAudioUrl),playAudioType);
  }
 else {
    videoPlayer.preparePlayer(Uri.parse(playVideoUrl),playVideoType);
  }
  videoPlayer.setPlayWhenReady(isAutoplay);
  isLoading=false;
  if (videoPlayer.getDuration() != C.TIME_UNSET) {
    controlsView.setDuration((int)(videoPlayer.getDuration() / 1000));
  }
 else {
    controlsView.setDuration(0);
  }
  updateFullscreenButton();
  updateShareButton();
  updateInlineButton();
  controlsView.invalidate();
  if (seekToTime != -1) {
    videoPlayer.seekTo(seekToTime * 1000);
  }
}
