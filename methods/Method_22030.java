/** 
 * ?????????????.
 * @param shardingItems ???????
 */
public void registerStart(final Collection<Integer> shardingItems){
  for (  int each : shardingItems) {
    jobNodeStorage.createJobNodeIfNeeded(GuaranteeNode.getStartedNode(each));
  }
}
