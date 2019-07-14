@Override public long readDiscontinuity(){
  long positionUs=periods[0].readDiscontinuity();
  for (int i=1; i < periods.length; i++) {
    if (periods[i].readDiscontinuity() != C.TIME_UNSET) {
      throw new IllegalStateException("Child reported discontinuity.");
    }
  }
  if (positionUs != C.TIME_UNSET) {
    for (    MediaPeriod enabledPeriod : enabledPeriods) {
      if (enabledPeriod != periods[0] && enabledPeriod.seekToUs(positionUs) != positionUs) {
        throw new IllegalStateException("Unexpected child seekToUs result.");
      }
    }
  }
  return positionUs;
}
