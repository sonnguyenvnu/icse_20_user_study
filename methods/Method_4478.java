private boolean shouldTransitionToReadyState(boolean renderersReadyOrEnded){
  if (enabledRenderers.length == 0) {
    return isTimelineReady();
  }
  if (!renderersReadyOrEnded) {
    return false;
  }
  if (!playbackInfo.isLoading) {
    return true;
  }
  MediaPeriodHolder loadingHolder=queue.getLoadingPeriod();
  boolean bufferedToEnd=loadingHolder.isFullyBuffered() && loadingHolder.info.isFinal;
  return bufferedToEnd || loadControl.shouldStartPlayback(getTotalBufferedDurationUs(),mediaClock.getPlaybackParameters().speed,rebuffering);
}
