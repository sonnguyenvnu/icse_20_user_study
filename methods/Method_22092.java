/** 
 * ???????????.
 */
public void setReshardingFlag(){
  jobNodeStorage.createJobNodeIfNeeded(ShardingNode.NECESSARY);
}
