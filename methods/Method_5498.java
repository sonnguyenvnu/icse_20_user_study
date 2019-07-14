@Override public long readDiscontinuity(){
  if (!notifiedReadingStarted) {
    eventDispatcher.readingStarted();
    notifiedReadingStarted=true;
  }
  return C.TIME_UNSET;
}
