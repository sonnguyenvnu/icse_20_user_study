/** 
 * ??????????????.
 * @return ????????????
 */
public boolean isAllCompleted(){
  return jobNodeStorage.isJobNodeExisted(GuaranteeNode.COMPLETED_ROOT) && configService.load(false).getTypeConfig().getCoreConfig().getShardingTotalCount() <= jobNodeStorage.getJobNodeChildrenKeys(GuaranteeNode.COMPLETED_ROOT).size();
}
