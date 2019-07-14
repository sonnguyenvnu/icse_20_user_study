@Override public TxContext txContext(String groupId){
  return attachmentCache.attachment(groupId + ".dtx");
}
