private int getUpdateType(TLRPC.Update update){
  if (update instanceof TLRPC.TL_updateNewMessage || update instanceof TLRPC.TL_updateReadMessagesContents || update instanceof TLRPC.TL_updateReadHistoryInbox || update instanceof TLRPC.TL_updateReadHistoryOutbox || update instanceof TLRPC.TL_updateDeleteMessages || update instanceof TLRPC.TL_updateWebPage || update instanceof TLRPC.TL_updateEditMessage || update instanceof TLRPC.TL_updateFolderPeers) {
    return 0;
  }
 else   if (update instanceof TLRPC.TL_updateNewEncryptedMessage) {
    return 1;
  }
 else   if (update instanceof TLRPC.TL_updateNewChannelMessage || update instanceof TLRPC.TL_updateDeleteChannelMessages || update instanceof TLRPC.TL_updateEditChannelMessage || update instanceof TLRPC.TL_updateChannelWebPage) {
    return 2;
  }
 else {
    return 3;
  }
}
