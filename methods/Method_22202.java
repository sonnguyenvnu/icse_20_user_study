/** 
 * End the application.
 */
private void endApplication(@Nullable Thread uncaughtExceptionThread,Throwable th){
  final boolean letDefaultHandlerEndApplication=config.alsoReportToAndroidFramework();
  final boolean handlingUncaughtException=uncaughtExceptionThread != null;
  if (handlingUncaughtException && letDefaultHandlerEndApplication && defaultExceptionHandler != null) {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Handing Exception on to default ExceptionHandler");
    defaultExceptionHandler.uncaughtException(uncaughtExceptionThread,th);
  }
 else {
    processFinisher.endApplication();
  }
}
