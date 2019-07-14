public void resumeJobsEndingWith(String jobGroupSuffix) throws Exception {
  resumeJobs(GroupMatcher.<JobKey>groupEndsWith(jobGroupSuffix));
}
