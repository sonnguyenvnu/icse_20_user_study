@Override protected void runAnalysis(PmdRunnable runnable){
  reports.add(runnable.call());
}
