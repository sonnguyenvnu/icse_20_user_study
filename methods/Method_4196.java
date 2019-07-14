@Override public void play(){
  playing=true;
  if (isInitialized()) {
    audioTrackPositionTracker.start();
    audioTrack.play();
  }
}
