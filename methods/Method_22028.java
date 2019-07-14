/** 
 * ??????????.
 */
public void removeFailoverInfo(){
  for (  String each : jobNodeStorage.getJobNodeChildrenKeys(ShardingNode.ROOT)) {
    jobNodeStorage.removeJobNodeIfExisted(FailoverNode.getExecutionFailoverNode(Integer.parseInt(each)));
  }
}
