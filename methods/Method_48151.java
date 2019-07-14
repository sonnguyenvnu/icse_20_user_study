public JanusGraphManagement.IndexJobFuture getScanJobStatus(Object jobId){
  return scanner.getRunningJob(jobId);
}
