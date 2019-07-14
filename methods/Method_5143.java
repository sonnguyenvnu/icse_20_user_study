@Override public long getNextLoadPositionUs(){
  if (isPendingReset()) {
    return pendingResetPositionUs;
  }
 else {
    return loadingFinished ? C.TIME_END_OF_SOURCE : getLastMediaChunk().endTimeUs;
  }
}
