@Override public void onStateChanged(boolean playWhenReady,int playbackState){
  if (playbackState != ExoPlayer.STATE_BUFFERING) {
    if (videoPlayer.getDuration() != C.TIME_UNSET) {
      controlsView.setDuration((int)(videoPlayer.getDuration() / 1000));
    }
 else {
      controlsView.setDuration(0);
    }
  }
  if (playbackState != ExoPlayer.STATE_ENDED && playbackState != ExoPlayer.STATE_IDLE && videoPlayer.isPlaying()) {
    delegate.onPlayStateChanged(this,true);
  }
 else {
    delegate.onPlayStateChanged(this,false);
  }
  if (videoPlayer.isPlaying() && playbackState != ExoPlayer.STATE_ENDED) {
    updatePlayButton();
  }
 else {
    if (playbackState == ExoPlayer.STATE_ENDED) {
      isCompleted=true;
      videoPlayer.pause();
      videoPlayer.seekTo(0);
      updatePlayButton();
      controlsView.show(true,true);
    }
  }
}
