/** 
 * ?????????????.
 * @param shardingItems ???????
 */
public void registerComplete(final Collection<Integer> shardingItems){
  for (  int each : shardingItems) {
    jobNodeStorage.createJobNodeIfNeeded(GuaranteeNode.getCompletedNode(each));
  }
}
