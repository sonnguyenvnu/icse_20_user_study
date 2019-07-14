@Override public int skipData(long positionUs){
  if (isPendingReset()) {
    return 0;
  }
  int skipCount;
  if (loadingFinished && positionUs > primarySampleQueue.getLargestQueuedTimestampUs()) {
    skipCount=primarySampleQueue.advanceToEnd();
  }
 else {
    skipCount=primarySampleQueue.advanceTo(positionUs,true,true);
    if (skipCount == SampleQueue.ADVANCE_FAILED) {
      skipCount=0;
    }
  }
  maybeNotifyPrimaryTrackFormatChanged();
  return skipCount;
}
