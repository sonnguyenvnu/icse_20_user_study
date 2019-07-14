@Override public void workerIterationStart(Configuration jobConfig,Configuration graphConfig,ScanMetrics metrics){
  try {
    open(graphConfig);
    job.workerIterationStart(graph.get(),jobConfig,metrics);
  }
 catch (  Throwable e) {
    close();
    throw e;
  }
}
