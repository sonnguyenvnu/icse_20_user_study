@Override public boolean isEligibleForJobRunning(){
  LiteJobConfiguration liteJobConfig=configService.load(true);
  if (liteJobConfig.getTypeConfig() instanceof DataflowJobConfiguration) {
    return !shardingService.isNeedSharding() && ((DataflowJobConfiguration)liteJobConfig.getTypeConfig()).isStreamingProcess();
  }
  return !shardingService.isNeedSharding();
}
