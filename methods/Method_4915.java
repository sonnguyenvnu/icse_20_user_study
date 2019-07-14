/** 
 * Returns whether the period is fully buffered. 
 */
public boolean isFullyBuffered(){
  return prepared && (!hasEnabledTracks || mediaPeriod.getBufferedPositionUs() == C.TIME_END_OF_SOURCE);
}
