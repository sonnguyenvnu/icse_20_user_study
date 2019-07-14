/** 
 * This is called when recoverable exceptions occur at runtime. By default they are ignored and Epoxy will recover, but you can override this to be aware of when they happen. <p> A common use for this is being aware of duplicates when  {@link #setFilterDuplicates(boolean)}is enabled. <p> By default the global exception handler provided by {@link #setGlobalExceptionHandler(ExceptionHandler)}is called with the exception. Overriding this allows you to provide your own handling for a controller.
 */
protected void onExceptionSwallowed(@NonNull RuntimeException exception){
  globalExceptionHandler.onException(this,exception);
}
