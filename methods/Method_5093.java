@Override public void seekTo(int windowIndex,long positionMs){
  verifyApplicationThread();
  analyticsCollector.notifySeekStarted();
  player.seekTo(windowIndex,positionMs);
}
