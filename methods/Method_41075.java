public void resumeJobsStartingWith(String jobGroupPrefix) throws Exception {
  resumeJobs(GroupMatcher.<JobKey>groupStartsWith(jobGroupPrefix));
}
