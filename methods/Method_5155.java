@Override public long readDiscontinuity(){
  if (isPendingInitialDiscontinuity()) {
    long initialDiscontinuityUs=pendingInitialDiscontinuityPositionUs;
    pendingInitialDiscontinuityPositionUs=C.TIME_UNSET;
    long childDiscontinuityUs=readDiscontinuity();
    return childDiscontinuityUs != C.TIME_UNSET ? childDiscontinuityUs : initialDiscontinuityUs;
  }
  long discontinuityUs=mediaPeriod.readDiscontinuity();
  if (discontinuityUs == C.TIME_UNSET) {
    return C.TIME_UNSET;
  }
  Assertions.checkState(discontinuityUs >= startUs);
  Assertions.checkState(endUs == C.TIME_END_OF_SOURCE || discontinuityUs <= endUs);
  return discontinuityUs;
}
