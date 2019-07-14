public boolean performInteractions(@NonNull final File reportFile){
  final ExecutorService executorService=Executors.newCachedThreadPool();
  final List<Future<Boolean>> futures=new ArrayList<>();
  for (  final ReportInteraction reportInteraction : reportInteractions) {
    futures.add(executorService.submit(() -> {
      if (ACRA.DEV_LOGGING)       ACRA.log.d(ACRA.LOG_TAG,"Calling ReportInteraction of class " + reportInteraction.getClass().getName());
      return reportInteraction.performInteraction(context,config,reportFile);
    }
));
  }
  boolean sendReports=true;
  for (  Future<Boolean> future : futures) {
    do {
      try {
        sendReports&=future.get();
      }
 catch (      InterruptedException ignored) {
      }
catch (      ExecutionException e) {
        break;
      }
    }
 while (!future.isDone());
  }
  return sendReports;
}
