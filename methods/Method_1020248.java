public static synchronized void configure(WorkerIdStrategy custom){
  if (workerIdStrategy == custom)   return;
  if (workerIdStrategy != null)   workerIdStrategy.release();
  workerIdStrategy=custom;
  workerIdStrategy.initialize();
  idWorker=new IdWorker(workerIdStrategy.availableWorkerId());
}
