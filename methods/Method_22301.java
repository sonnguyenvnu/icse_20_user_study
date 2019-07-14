/** 
 * Creates an Intent that can be used to create and show a CrashReportDialog.
 * @param reportFile Error report file to display in the crash report dialog.
 */
@NonNull private Intent createCrashReportDialogIntent(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull File reportFile){
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Creating DialogIntent for " + reportFile);
  final Intent dialogIntent=new Intent(context,ConfigUtils.getPluginConfiguration(config,DialogConfiguration.class).reportDialogClass());
  dialogIntent.putExtra(EXTRA_REPORT_FILE,reportFile);
  dialogIntent.putExtra(EXTRA_REPORT_CONFIG,config);
  return dialogIntent;
}
