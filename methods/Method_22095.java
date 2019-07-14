private void blockUntilShardingCompleted(){
  while (!leaderService.isLeaderUntilBlock() && (jobNodeStorage.isJobNodeExisted(ShardingNode.NECESSARY) || jobNodeStorage.isJobNodeExisted(ShardingNode.PROCESSING))) {
    log.debug("Job '{}' sleep short time until sharding completed.",jobName);
    BlockUtils.waitingShortTime();
  }
}
