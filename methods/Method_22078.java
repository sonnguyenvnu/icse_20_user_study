/** 
 * ????????.
 * @param shardingContexts ?????
 */
public void registerJobCompleted(final ShardingContexts shardingContexts){
  JobRegistry.getInstance().setJobRunning(jobName,false);
  if (!configService.load(true).isMonitorExecution()) {
    return;
  }
  for (  int each : shardingContexts.getShardingItemParameters().keySet()) {
    jobNodeStorage.removeJobNodeIfExisted(ShardingNode.getRunningNode(each));
  }
}
