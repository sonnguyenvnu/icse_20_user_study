@Override public long seekToUs(long positionUs){
  PreparedState preparedState=getPreparedState();
  SeekMap seekMap=preparedState.seekMap;
  boolean[] trackIsAudioVideoFlags=preparedState.trackIsAudioVideoFlags;
  positionUs=seekMap.isSeekable() ? positionUs : 0;
  notifyDiscontinuity=false;
  lastSeekPositionUs=positionUs;
  if (isPendingReset()) {
    pendingResetPositionUs=positionUs;
    return positionUs;
  }
  if (dataType != C.DATA_TYPE_MEDIA_PROGRESSIVE_LIVE && seekInsideBufferUs(trackIsAudioVideoFlags,positionUs)) {
    return positionUs;
  }
  pendingDeferredRetry=false;
  pendingResetPositionUs=positionUs;
  loadingFinished=false;
  if (loader.isLoading()) {
    loader.cancelLoading();
  }
 else {
    for (    SampleQueue sampleQueue : sampleQueues) {
      sampleQueue.reset();
    }
  }
  return positionUs;
}
