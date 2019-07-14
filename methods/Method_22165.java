@Override public void updateJobSettings(final JobSettings jobSettings){
  Preconditions.checkArgument(!Strings.isNullOrEmpty(jobSettings.getJobName()),"jobName can not be empty.");
  Preconditions.checkArgument(!Strings.isNullOrEmpty(jobSettings.getCron()),"cron can not be empty.");
  Preconditions.checkArgument(jobSettings.getShardingTotalCount() > 0,"shardingTotalCount should larger than zero.");
  JobNodePath jobNodePath=new JobNodePath(jobSettings.getJobName());
  regCenter.update(jobNodePath.getConfigNodePath(),LiteJobConfigurationGsonFactory.toJsonForObject(jobSettings));
}
