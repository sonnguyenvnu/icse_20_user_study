@Override public boolean shouldStartCollecting(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull ReportBuilder reportBuilder){
  try {
    final LimiterConfiguration limiterConfiguration=ConfigUtils.getPluginConfiguration(config,LimiterConfiguration.class);
    final ReportLocator reportLocator=new ReportLocator(context);
    if (reportLocator.getApprovedReports().length + reportLocator.getUnapprovedReports().length >= limiterConfiguration.failedReportLimit()) {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Reached failedReportLimit, not collecting");
      return false;
    }
    final List<LimiterData.ReportMetadata> reportMetadata=loadLimiterData(context,limiterConfiguration).getReportMetadata();
    if (reportMetadata.size() >= limiterConfiguration.overallLimit()) {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Reached overallLimit, not collecting");
      return false;
    }
  }
 catch (  IOException e) {
    ACRA.log.w(LOG_TAG,"Failed to load LimiterData",e);
  }
  return true;
}
