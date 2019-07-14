public void markMentionsAsRead(long dialogId){
  if ((int)dialogId == 0) {
    return;
  }
  MessagesStorage.getInstance(currentAccount).resetMentionsCount(dialogId,0);
  TLRPC.TL_messages_readMentions req=new TLRPC.TL_messages_readMentions();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)dialogId);
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
}
