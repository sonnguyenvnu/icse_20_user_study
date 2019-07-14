/** 
 * Store a report
 * @param file      the file to store in
 * @param crashData the content
 */
private void saveCrashReportFile(@NonNull File file,@NonNull CrashReportData crashData){
  try {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Writing crash report file " + file);
    final CrashReportPersister persister=new CrashReportPersister();
    persister.store(crashData,file);
  }
 catch (  Exception e) {
    ACRA.log.e(LOG_TAG,"An error occurred while writing the report file...",e);
  }
}
