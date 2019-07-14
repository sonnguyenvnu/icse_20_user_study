public boolean removeBlockedJob(JobKey key){
  return blockedJobs.remove(key);
}
