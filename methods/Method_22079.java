/** 
 * ????????????.
 * @param items ??????????
 */
public void clearRunningInfo(final List<Integer> items){
  for (  int each : items) {
    jobNodeStorage.removeJobNodeIfExisted(ShardingNode.getRunningNode(each));
  }
}
