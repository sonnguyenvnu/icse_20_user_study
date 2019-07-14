@Override public synchronized void setEstimatedTimeToReleaseAndAcquireTrigger(long estimate){
  ClusteredJobStore cjs=clusteredJobStore;
  if (cjs != null) {
    cjs.setEstimatedTimeToReleaseAndAcquireTrigger(estimate);
  }
 else {
    this.estimatedTimeToReleaseAndAcquireTrigger=estimate;
  }
}
