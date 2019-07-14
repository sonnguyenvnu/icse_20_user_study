/** 
 * ?????????????????????.
 * @return ???????????????????
 */
public List<Integer> getLocalTakeOffItems(){
  List<Integer> shardingItems=shardingService.getLocalShardingItems();
  List<Integer> result=new ArrayList<>(shardingItems.size());
  for (  int each : shardingItems) {
    if (jobNodeStorage.isJobNodeExisted(FailoverNode.getExecutionFailoverNode(each))) {
      result.add(each);
    }
  }
  return result;
}
