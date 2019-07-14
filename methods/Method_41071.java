public void pauseJobGroup(String jobGroup) throws Exception {
  pauseJobs(GroupMatcher.<JobKey>groupEquals(jobGroup));
}
