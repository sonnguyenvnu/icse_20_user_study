@Override public void retry(){
  if (mediaSource != null && (playbackError != null || playbackInfo.playbackState == Player.STATE_IDLE)) {
    prepare(mediaSource,false,false);
  }
}
