public long getJobsExecutingMostRecentSample(){
  return jobsExecutingCount.getMostRecentSample().getCounterValue();
}
