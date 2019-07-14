public void reportSpam(final long dialogId,TLRPC.User currentUser,TLRPC.Chat currentChat,TLRPC.EncryptedChat currentEncryptedChat){
  if (currentUser == null && currentChat == null && currentEncryptedChat == null) {
    return;
  }
  SharedPreferences.Editor editor=notificationsPreferences.edit();
  editor.putInt("spam3_" + dialogId,1);
  editor.commit();
  if ((int)dialogId == 0) {
    if (currentEncryptedChat == null || currentEncryptedChat.access_hash == 0) {
      return;
    }
    TLRPC.TL_messages_reportEncryptedSpam req=new TLRPC.TL_messages_reportEncryptedSpam();
    req.peer=new TLRPC.TL_inputEncryptedChat();
    req.peer.chat_id=currentEncryptedChat.id;
    req.peer.access_hash=currentEncryptedChat.access_hash;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  }
 else {
    TLRPC.TL_messages_reportSpam req=new TLRPC.TL_messages_reportSpam();
    if (currentChat != null) {
      req.peer=getInputPeer(-currentChat.id);
    }
 else     if (currentUser != null) {
      req.peer=getInputPeer(currentUser.id);
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  }
}
