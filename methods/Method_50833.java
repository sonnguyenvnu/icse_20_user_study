/** 
 * Stops global tracking. Stops the wall clock. All further operations will be treated as NOOP.
 * @return The timed data obtained through the run.
 */
public static TimingReport stopGlobalTracking(){
  if (!trackTime) {
    return null;
  }
  finishThread();
  trackTime=false;
  final TimedResult unaccountedResult=ACCUMULATED_RESULTS.get(new TimedOperationKey(TimedOperationCategory.UNACCOUNTED,null));
  unaccountedResult.totalTimeNanos.set(unaccountedResult.selfTimeNanos.get());
  unaccountedResult.callCount.set(0);
  return new TimingReport(System.currentTimeMillis() - wallClockStartMillis,ACCUMULATED_RESULTS);
}
