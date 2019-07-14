/** 
 * ????????????????.
 * @param items ??????????
 * @return ??????????????
 */
public boolean hasRunningItems(final Collection<Integer> items){
  LiteJobConfiguration jobConfig=configService.load(true);
  if (null == jobConfig || !jobConfig.isMonitorExecution()) {
    return false;
  }
  for (  int each : items) {
    if (jobNodeStorage.isJobNodeExisted(ShardingNode.getRunningNode(each))) {
      return true;
    }
  }
  return false;
}
