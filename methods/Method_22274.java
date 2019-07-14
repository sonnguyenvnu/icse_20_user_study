/** 
 * Send report via all senders.
 * @param reportFile Report to send.
 * @return if distributing was successful
 */
public boolean distribute(@NonNull File reportFile){
  ACRA.log.i(LOG_TAG,"Sending report " + reportFile);
  try {
    final CrashReportPersister persister=new CrashReportPersister();
    final CrashReportData previousCrashReport=persister.load(reportFile);
    sendCrashReport(previousCrashReport);
    IOUtils.deleteFile(reportFile);
    return true;
  }
 catch (  RuntimeException e) {
    ACRA.log.e(LOG_TAG,"Failed to send crash reports for " + reportFile,e);
    IOUtils.deleteFile(reportFile);
  }
catch (  IOException e) {
    ACRA.log.e(LOG_TAG,"Failed to load crash report for " + reportFile,e);
    IOUtils.deleteFile(reportFile);
  }
catch (  JSONException e) {
    ACRA.log.e(LOG_TAG,"Failed to load crash report for " + reportFile,e);
    IOUtils.deleteFile(reportFile);
  }
catch (  ReportSenderException e) {
    ACRA.log.e(LOG_TAG,"Failed to send crash report for " + reportFile,e);
  }
  return false;
}
