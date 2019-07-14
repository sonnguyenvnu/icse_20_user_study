public void setChatInfo(TLRPC.ChatFull value){
  chatInfo=value;
  if (chatInfo != null && chatInfo.migrated_from_chat_id != 0 && mergeDialogId == 0) {
    mergeDialogId=-chatInfo.migrated_from_chat_id;
    DataQuery.getInstance(currentAccount).getMediaCounts(mergeDialogId,classGuid);
  }
  fetchUsersFromChannelInfo();
}
