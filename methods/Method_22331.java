@Override public boolean shouldSendReport(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull CrashReportData crashReportData){
  try {
    final LimiterConfiguration limiterConfiguration=ConfigUtils.getPluginConfiguration(config,LimiterConfiguration.class);
    final LimiterData limiterData=loadLimiterData(context,limiterConfiguration);
    int sameTrace=0;
    int sameClass=0;
    final LimiterData.ReportMetadata m=new LimiterData.ReportMetadata(crashReportData);
    for (    LimiterData.ReportMetadata metadata : limiterData.getReportMetadata()) {
      if (m.getStacktrace().equals(metadata.getStacktrace())) {
        sameTrace++;
      }
      if (m.getExceptionClass().equals(metadata.getExceptionClass())) {
        sameClass++;
      }
    }
    if (sameTrace >= limiterConfiguration.stacktraceLimit()) {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Reached stacktraceLimit, not sending");
      return false;
    }
    if (sameClass >= limiterConfiguration.exceptionClassLimit()) {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Reached exceptionClassLimit, not sending");
      return false;
    }
    limiterData.getReportMetadata().add(m);
    limiterData.store(context);
  }
 catch (  IOException|JSONException e) {
    ACRA.log.w(LOG_TAG,"Failed to load LimiterData",e);
  }
  return true;
}
