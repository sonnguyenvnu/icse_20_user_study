public void pinMessage(TLRPC.Chat chat,TLRPC.User user,int id,boolean notify){
  if (chat == null && user == null) {
    return;
  }
  TLRPC.TL_messages_updatePinnedMessage req=new TLRPC.TL_messages_updatePinnedMessage();
  req.peer=getInputPeer(chat != null ? -chat.id : user.id);
  req.id=id;
  req.silent=!notify;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      TLRPC.Updates updates=(TLRPC.Updates)response;
      processUpdates(updates,false);
    }
  }
);
}
