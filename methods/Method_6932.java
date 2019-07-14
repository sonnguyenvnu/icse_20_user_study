private static int getUpdatePts(TLRPC.Update update){
  if (update instanceof TLRPC.TL_updateDeleteMessages) {
    return ((TLRPC.TL_updateDeleteMessages)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateNewChannelMessage) {
    return ((TLRPC.TL_updateNewChannelMessage)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateReadHistoryOutbox) {
    return ((TLRPC.TL_updateReadHistoryOutbox)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateNewMessage) {
    return ((TLRPC.TL_updateNewMessage)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateEditMessage) {
    return ((TLRPC.TL_updateEditMessage)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateWebPage) {
    return ((TLRPC.TL_updateWebPage)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateReadHistoryInbox) {
    return ((TLRPC.TL_updateReadHistoryInbox)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateChannelWebPage) {
    return ((TLRPC.TL_updateChannelWebPage)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateDeleteChannelMessages) {
    return ((TLRPC.TL_updateDeleteChannelMessages)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateEditChannelMessage) {
    return ((TLRPC.TL_updateEditChannelMessage)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateReadMessagesContents) {
    return ((TLRPC.TL_updateReadMessagesContents)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateChannelTooLong) {
    return ((TLRPC.TL_updateChannelTooLong)update).pts;
  }
 else   if (update instanceof TLRPC.TL_updateFolderPeers) {
    return ((TLRPC.TL_updateFolderPeers)update).pts;
  }
 else {
    return 0;
  }
}
