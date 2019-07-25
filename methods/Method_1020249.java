public static synchronized void configure(WorkerIdStrategy custom){
  if (workerIdStrategy != null)   workerIdStrategy.release();
  workerIdStrategy=custom;
  idWorker=new IdWorker(workerIdStrategy.availableWorkerId()){
    @Override public long getEpoch(){
      return Utils.midnightMillis();
    }
  }
;
}
