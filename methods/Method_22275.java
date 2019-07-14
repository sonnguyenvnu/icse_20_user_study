/** 
 * Sends the report with all configured ReportSenders. If at least one sender completed its job, the report is considered as sent and will not be sent again for failing senders.
 * @param errorContent Crash data.
 * @throws ReportSenderException if unable to send the crash report.
 */
private void sendCrashReport(@NonNull CrashReportData errorContent) throws ReportSenderException {
  if (!isDebuggable() || config.sendReportsInDevMode()) {
    final List<RetryPolicy.FailedSender> failedSenders=new LinkedList<>();
    for (    ReportSender sender : reportSenders) {
      try {
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Sending report using " + sender.getClass().getName());
        sender.send(context,errorContent);
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Sent report using " + sender.getClass().getName());
      }
 catch (      ReportSenderException e) {
        failedSenders.add(new RetryPolicy.FailedSender(sender,e));
      }
    }
    final InstanceCreator instanceCreator=new InstanceCreator();
    if (failedSenders.isEmpty()) {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Report was sent by all senders");
    }
 else     if (instanceCreator.create(config.retryPolicyClass(),DefaultRetryPolicy::new).shouldRetrySend(reportSenders,failedSenders)) {
      final Throwable firstFailure=failedSenders.get(0).getException();
      throw new ReportSenderException("Policy marked this task as incomplete. ACRA will try to send this report again.",firstFailure);
    }
 else {
      final StringBuilder builder=new StringBuilder("ReportSenders of classes [");
      for (      final RetryPolicy.FailedSender failedSender : failedSenders) {
        builder.append(failedSender.getSender().getClass().getName());
        builder.append(", ");
      }
      builder.append("] failed, but Policy marked this task as complete. ACRA will not send this report again.");
      ACRA.log.w(LOG_TAG,builder.toString());
    }
  }
}
