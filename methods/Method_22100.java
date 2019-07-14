/** 
 * ?????????????????.
 * @return ???????????????
 */
public boolean hasShardingInfoInOfflineServers(){
  List<String> onlineInstances=jobNodeStorage.getJobNodeChildrenKeys(InstanceNode.ROOT);
  int shardingTotalCount=configService.load(true).getTypeConfig().getCoreConfig().getShardingTotalCount();
  for (int i=0; i < shardingTotalCount; i++) {
    if (!onlineInstances.contains(jobNodeStorage.getJobNodeData(ShardingNode.getInstanceNode(i)))) {
      return true;
    }
  }
  return false;
}
