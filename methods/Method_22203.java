/** 
 * Starts a Process to start sending outstanding error reports.
 * @param onlySendSilentReports If true then only send silent reports.
 */
private void sendReport(@NonNull File report,boolean onlySendSilentReports){
  if (enabled) {
    schedulerStarter.scheduleReports(report,onlySendSilentReports);
  }
 else {
    ACRA.log.w(LOG_TAG,"Would be sending reports, but ACRA is disabled");
  }
}
