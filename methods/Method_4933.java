/** 
 * Returns whether a new loading media period should be enqueued, if available. 
 */
public boolean shouldLoadNextMediaPeriod(){
  return loading == null || (!loading.info.isFinal && loading.isFullyBuffered() && loading.info.durationUs != C.TIME_UNSET && length < MAXIMUM_BUFFER_AHEAD_PERIODS);
}
