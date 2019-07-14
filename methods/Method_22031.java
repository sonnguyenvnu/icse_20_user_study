/** 
 * ??????????????.
 * @return ????????????
 */
public boolean isAllStarted(){
  return jobNodeStorage.isJobNodeExisted(GuaranteeNode.STARTED_ROOT) && configService.load(false).getTypeConfig().getCoreConfig().getShardingTotalCount() == jobNodeStorage.getJobNodeChildrenKeys(GuaranteeNode.STARTED_ROOT).size();
}
