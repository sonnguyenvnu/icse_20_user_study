/** 
 * ?????????????????.
 * @param jobInstanceId ????????
 * @return ????????????
 */
public List<Integer> getFailoverItems(final String jobInstanceId){
  List<String> items=jobNodeStorage.getJobNodeChildrenKeys(ShardingNode.ROOT);
  List<Integer> result=new ArrayList<>(items.size());
  for (  String each : items) {
    int item=Integer.parseInt(each);
    String node=FailoverNode.getExecutionFailoverNode(item);
    if (jobNodeStorage.isJobNodeExisted(node) && jobInstanceId.equals(jobNodeStorage.getJobNodeDataDirectly(node))) {
      result.add(item);
    }
  }
  Collections.sort(result);
  return result;
}
