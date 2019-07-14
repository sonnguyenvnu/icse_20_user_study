public long getJobsCompletedMostRecentSample(){
  return jobsCompletedCount.getMostRecentSample().getCounterValue();
}
