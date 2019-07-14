private void streamingExecute(final ShardingContext shardingContext){
  List<Object> data=fetchData(shardingContext);
  while (null != data && !data.isEmpty()) {
    processData(shardingContext,data);
    if (!getJobFacade().isEligibleForJobRunning()) {
      break;
    }
    data=fetchData(shardingContext);
  }
}
