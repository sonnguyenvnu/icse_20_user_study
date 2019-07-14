/** 
 * pass-through to default handler
 * @param t the crashed thread
 * @param e the uncaught exception
 */
public void handReportToDefaultExceptionHandler(@Nullable Thread t,@NonNull Throwable e){
  if (defaultExceptionHandler != null) {
    ACRA.log.i(LOG_TAG,"ACRA is disabled for " + context.getPackageName() + " - forwarding uncaught Exception on to default ExceptionHandler");
    defaultExceptionHandler.uncaughtException(t,e);
  }
 else {
    ACRA.log.e(LOG_TAG,"ACRA is disabled for " + context.getPackageName() + " - no default ExceptionHandler");
    ACRA.log.e(LOG_TAG,"ACRA caught a " + e.getClass().getSimpleName() + " for " + context.getPackageName(),e);
  }
}
