private void setPlayWhenReadyInternal(boolean playWhenReady) throws ExoPlaybackException {
  rebuffering=false;
  this.playWhenReady=playWhenReady;
  if (!playWhenReady) {
    stopRenderers();
    updatePlaybackPositions();
  }
 else {
    if (playbackInfo.playbackState == Player.STATE_READY) {
      startRenderers();
      handler.sendEmptyMessage(MSG_DO_SOME_WORK);
    }
 else     if (playbackInfo.playbackState == Player.STATE_BUFFERING) {
      handler.sendEmptyMessage(MSG_DO_SOME_WORK);
    }
  }
}
