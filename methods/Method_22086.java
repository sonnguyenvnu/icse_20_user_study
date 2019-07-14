/** 
 * ??????????.
 * @param items ????????????
 * @return ????????
 */
public List<Integer> getDisabledItems(final List<Integer> items){
  List<Integer> result=new ArrayList<>(items.size());
  for (  int each : items) {
    if (jobNodeStorage.isJobNodeExisted(ShardingNode.getDisabledNode(each))) {
      result.add(each);
    }
  }
  return result;
}
