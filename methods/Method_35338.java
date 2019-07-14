@Override public boolean pause(){
  if (mPlayer.isPlaying()) {
    mPlayer.pause();
    isPaused=true;
    notifyPlayStatusChanged(false);
    return true;
  }
  return false;
}
