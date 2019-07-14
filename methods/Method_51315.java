@Override protected void runAnalysis(PmdRunnable runnable){
  completionService.submit(runnable);
  submittedTasks++;
}
