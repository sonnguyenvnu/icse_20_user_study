private String buildTaskId(final LiteJobConfiguration liteJobConfig,final List<Integer> shardingItems){
  JobInstance jobInstance=JobRegistry.getInstance().getJobInstance(jobName);
  return Joiner.on("@-@").join(liteJobConfig.getJobName(),Joiner.on(",").join(shardingItems),"READY",null == jobInstance.getJobInstanceId() ? "127.0.0.1@-@1" : jobInstance.getJobInstanceId());
}
