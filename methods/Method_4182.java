/** 
 * Returns whether the track is in an invalid state and must be recreated. 
 */
public boolean isStalled(long writtenFrames){
  return forceResetWorkaroundTimeMs != C.TIME_UNSET && writtenFrames > 0 && SystemClock.elapsedRealtime() - forceResetWorkaroundTimeMs >= FORCE_RESET_WORKAROUND_TIMEOUT_MS;
}
