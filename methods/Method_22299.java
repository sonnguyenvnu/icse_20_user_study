/** 
 * Send crash report given user's comment and email address.
 * @param comment   Comment (may be null) provided by the user.
 * @param userEmail Email address (may be null) provided by the user.
 */
public void sendCrash(@Nullable String comment,@Nullable String userEmail){
  new Thread(() -> {
    try {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Add user comment to " + reportFile);
      final CrashReportData crashData=getReportData();
      crashData.put(USER_COMMENT,comment == null ? "" : comment);
      crashData.put(USER_EMAIL,userEmail == null ? "" : userEmail);
      new CrashReportPersister().store(crashData,reportFile);
    }
 catch (    IOException|JSONException e) {
      ACRA.log.w(LOG_TAG,"User comment not added: ",e);
    }
    new SchedulerStarter(context,config).scheduleReports(reportFile,false);
  }
).start();
}
