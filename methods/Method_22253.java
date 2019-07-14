/** 
 * Collects crash data.
 * @param builder ReportBuilder for whom to crete the crash report.
 * @return CrashReportData identifying the current crash.
 */
@NonNull public CrashReportData createCrashData(@NonNull final ReportBuilder builder){
  final ExecutorService executorService=config.parallel() ? Executors.newCachedThreadPool() : Executors.newSingleThreadExecutor();
  final CrashReportData crashReportData=new CrashReportData();
  final List<Future<?>> futures=new ArrayList<>();
  for (  final Collector collector : collectors) {
    futures.add(executorService.submit(() -> {
      try {
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Calling collector " + collector.getClass().getName());
        collector.collect(context,config,builder,crashReportData);
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Collector " + collector.getClass().getName() + " completed");
      }
 catch (      CollectorException e) {
        ACRA.log.w(LOG_TAG,e);
      }
catch (      Exception t) {
        ACRA.log.e(LOG_TAG,"Error in collector " + collector.getClass().getSimpleName(),t);
      }
    }
));
  }
  for (  Future<?> future : futures) {
    while (!future.isDone()) {
      try {
        future.get();
      }
 catch (      InterruptedException ignored) {
      }
catch (      ExecutionException e) {
        break;
      }
    }
  }
  return crashReportData;
}
