/** 
 * ????????.
 * @param item ???
 * @param nodeName ?????
 * @return ??????
 */
public String getShardingNodePath(final String item,final String nodeName){
  return String.format("%s/%s/%s",getShardingNodePath(),item,nodeName);
}
