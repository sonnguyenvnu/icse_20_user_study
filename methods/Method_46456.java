/** 
 * ??????????????
 * @param groupId groupId
 */
@Override public void destroyTx(String groupId){
  attachmentCache.remove(groupId + ".dtx");
  log.debug("Destroy TxContext[{}]",groupId);
}
