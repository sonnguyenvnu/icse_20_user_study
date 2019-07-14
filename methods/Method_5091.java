@Override public int getPlaybackState(){
  verifyApplicationThread();
  return player.getPlaybackState();
}
