private void maybeUpdateLoadingPeriod() throws IOException {
  queue.reevaluateBuffer(rendererPositionUs);
  if (queue.shouldLoadNextMediaPeriod()) {
    MediaPeriodInfo info=queue.getNextMediaPeriodInfo(rendererPositionUs,playbackInfo);
    if (info == null) {
      maybeThrowSourceInfoRefreshError();
    }
 else {
      MediaPeriod mediaPeriod=queue.enqueueNextMediaPeriod(rendererCapabilities,trackSelector,loadControl.getAllocator(),mediaSource,info);
      mediaPeriod.prepare(this,info.startPositionUs);
      setIsLoading(true);
      handleLoadingMediaPeriodChanged(false);
    }
  }
}
