/** 
 * Continues loading the media period at the given renderer position. Should only be called if this is the loading media period.
 * @param rendererPositionUs The load position in renderer time, in microseconds.
 */
public void continueLoading(long rendererPositionUs){
  Assertions.checkState(isLoadingMediaPeriod());
  long loadingPeriodPositionUs=toPeriodTime(rendererPositionUs);
  mediaPeriod.continueLoading(loadingPeriodPositionUs);
}
