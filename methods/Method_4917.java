/** 
 * Reevaluates the buffer of the media period at the given renderer position. Should only be called if this is the loading media period.
 * @param rendererPositionUs The playing position in renderer time, in microseconds.
 */
public void reevaluateBuffer(long rendererPositionUs){
  Assertions.checkState(isLoadingMediaPeriod());
  if (prepared) {
    mediaPeriod.reevaluateBuffer(toPeriodTime(rendererPositionUs));
  }
}
