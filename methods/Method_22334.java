@Override public void processReports(@NonNull Context context,@NonNull CoreConfiguration config,List<Report> reports){
  final LimiterConfiguration limiterConfiguration=ConfigUtils.getPluginConfiguration(config,LimiterConfiguration.class);
  if (limiterConfiguration.deleteReportsOnAppUpdate() || limiterConfiguration.resetLimitsOnAppUpdate()) {
    final SharedPreferences prefs=new SharedPreferencesFactory(context,config).create();
    final long lastVersionNr=prefs.getInt(ACRA.PREF_LAST_VERSION_NR,0);
    final int appVersion=getAppVersion(context);
    if (appVersion > lastVersionNr) {
      if (limiterConfiguration.deleteReportsOnAppUpdate()) {
        prefs.edit().putInt(ACRA.PREF_LAST_VERSION_NR,appVersion).apply();
        for (        Report report : reports) {
          report.delete();
        }
      }
      if (limiterConfiguration.resetLimitsOnAppUpdate()) {
        try {
          new LimiterData().store(context);
        }
 catch (        IOException e) {
          ACRA.log.w(LOG_TAG,"Failed to reset LimiterData",e);
        }
      }
    }
  }
}
