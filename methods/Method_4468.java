private void releaseInternal(){
  resetInternal(true,true,true,true);
  loadControl.onReleased();
  setState(Player.STATE_IDLE);
  internalPlaybackThread.quit();
synchronized (this) {
    released=true;
    notifyAll();
  }
}
