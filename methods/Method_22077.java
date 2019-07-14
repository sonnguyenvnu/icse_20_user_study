/** 
 * ????????.
 * @param shardingContexts ?????
 */
public void registerJobBegin(final ShardingContexts shardingContexts){
  JobRegistry.getInstance().setJobRunning(jobName,true);
  if (!configService.load(true).isMonitorExecution()) {
    return;
  }
  for (  int each : shardingContexts.getShardingItemParameters().keySet()) {
    jobNodeStorage.fillEphemeralJobNode(ShardingNode.getRunningNode(each),"");
  }
}
