@Override public boolean hasPendingData(){
  return isInitialized() && audioTrackPositionTracker.hasPendingData(getWrittenFrames());
}
