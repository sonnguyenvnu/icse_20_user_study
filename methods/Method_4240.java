@Override public boolean isReady(){
  return audioSink.hasPendingData() || super.isReady();
}
