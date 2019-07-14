@Override public void playToEndOfStream() throws WriteException {
  if (handledEndOfStream || !isInitialized()) {
    return;
  }
  if (drainAudioProcessorsToEndOfStream()) {
    audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
    audioTrack.stop();
    bytesUntilNextAvSync=0;
    handledEndOfStream=true;
  }
}
