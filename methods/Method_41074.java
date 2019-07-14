public void resumeJobGroup(String jobGroup) throws Exception {
  resumeJobs(GroupMatcher.<JobKey>groupEquals(jobGroup));
}
