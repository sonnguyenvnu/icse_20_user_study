@Override void collect(@NonNull ReportField reportField,@NonNull Context context,@NonNull CoreConfiguration config,@NonNull ReportBuilder reportBuilder,@NonNull CrashReportData target) throws Exception {
switch (reportField) {
case USER_EMAIL:
    target.put(ReportField.USER_EMAIL,new SharedPreferencesFactory(context,config).create().getString(ACRA.PREF_USER_EMAIL_ADDRESS,null));
  break;
case SHARED_PREFERENCES:
target.put(ReportField.SHARED_PREFERENCES,collect(context,config));
break;
default :
throw new IllegalArgumentException();
}
}
