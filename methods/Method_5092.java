@Override public void retry(){
  verifyApplicationThread();
  if (mediaSource != null && (getPlaybackError() != null || getPlaybackState() == Player.STATE_IDLE)) {
    prepare(mediaSource,false,false);
  }
}
