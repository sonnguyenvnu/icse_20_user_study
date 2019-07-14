@Override protected void onStopped(){
  updateCurrentPosition();
  audioSink.pause();
  super.onStopped();
}
