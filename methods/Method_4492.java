private long getTotalBufferedDurationUs(long bufferedPositionInLoadingPeriodUs){
  MediaPeriodHolder loadingPeriodHolder=queue.getLoadingPeriod();
  return loadingPeriodHolder == null ? 0 : bufferedPositionInLoadingPeriodUs - loadingPeriodHolder.toPeriodTime(rendererPositionUs);
}
