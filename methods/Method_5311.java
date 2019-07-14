@Override public long readDiscontinuity(){
  if (!notifiedReadingStarted) {
    eventDispatcher.readingStarted();
    notifiedReadingStarted=true;
  }
  if (notifyDiscontinuity && (loadingFinished || getExtractedSamplesCount() > extractedSamplesCountAtStartOfLoad)) {
    notifyDiscontinuity=false;
    return lastSeekPositionUs;
  }
  return C.TIME_UNSET;
}
