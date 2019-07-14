@Override public void postJobStatusTraceEvent(final String taskId,final State state,final String message){
  TaskContext taskContext=TaskContext.from(taskId);
  jobEventBus.post(new JobStatusTraceEvent(taskContext.getMetaInfo().getJobName(),taskContext.getId(),taskContext.getSlaveId(),Source.LITE_EXECUTOR,taskContext.getType(),taskContext.getMetaInfo().getShardingItems().toString(),state,message));
  if (!Strings.isNullOrEmpty(message)) {
    log.trace(message);
  }
}
