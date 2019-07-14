@Override public boolean performInteraction(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull File reportFile){
  final SharedPreferences prefs=new SharedPreferencesFactory(context,config).create();
  if (prefs.getBoolean(ACRA.PREF_ALWAYS_ACCEPT,false)) {
    return true;
  }
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Creating CrashReportDialog for " + reportFile);
  final Intent dialogIntent=createCrashReportDialogIntent(context,config,reportFile);
  dialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  context.startActivity(dialogIntent);
  return false;
}
