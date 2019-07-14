/** 
 * Returns whether periods after  {@code periodHolder} can be kept for playing given its previousduration.
 */
private boolean canKeepAfterMediaPeriodHolder(MediaPeriodHolder periodHolder,long previousDurationUs){
  return previousDurationUs == C.TIME_UNSET || previousDurationUs == periodHolder.info.durationUs;
}
