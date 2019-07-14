public long getGroupIdForUse(){
  return localSentGroupId != 0 ? localSentGroupId : messageOwner.grouped_id;
}
