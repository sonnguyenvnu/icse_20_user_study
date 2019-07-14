/** 
 * Starts a process to start sending outstanding error reports.
 * @param report                If not null, this report will be approved before scheduling.
 * @param onlySendSilentReports If true then only send silent reports.
 */
public void scheduleReports(@Nullable File report,boolean onlySendSilentReports){
  if (report != null) {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Mark " + report.getName() + " as approved.");
    final File approvedReport=new File(locator.getApprovedFolder(),report.getName());
    if (!report.renameTo(approvedReport)) {
      ACRA.log.w(LOG_TAG,"Could not rename approved report from " + report + " to " + approvedReport);
    }
  }
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Schedule report sending");
  senderScheduler.scheduleReportSending(onlySendSilentReports);
}
