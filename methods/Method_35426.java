@Override public void onPlaybackServiceBound(PlaybackService service){
  mPlayer=service;
  mPlayer.registerCallback(this);
}
