/** 
 * ??????????.
 * @param item ??????
 */
public void setCrashedFailoverFlag(final int item){
  if (!isFailoverAssigned(item)) {
    jobNodeStorage.createJobNodeIfNeeded(FailoverNode.getItemsNode(item));
  }
}
