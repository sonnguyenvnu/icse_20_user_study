/** 
 * ???????
 * @param groupId groupId
 */
@Override public void clearGroup(String groupId){
  this.attachmentCache.removeAll(groupId);
}
