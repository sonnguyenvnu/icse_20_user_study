public void sendNotificationCallback(final long dialogId,final int msgId,final byte[] data){
  AndroidUtilities.runOnUIThread(() -> {
    int lowerId=(int)dialogId;
    final String key=dialogId + "_" + msgId + "_" + Utilities.bytesToHex(data) + "_" + 0;
    waitingForCallback.put(key,true);
    if (lowerId > 0) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(lowerId);
      if (user == null) {
        user=MessagesStorage.getInstance(currentAccount).getUserSync(lowerId);
        if (user != null) {
          MessagesController.getInstance(currentAccount).putUser(user,true);
        }
      }
    }
 else {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lowerId);
      if (chat == null) {
        chat=MessagesStorage.getInstance(currentAccount).getChatSync(-lowerId);
        if (chat != null) {
          MessagesController.getInstance(currentAccount).putChat(chat,true);
        }
      }
    }
    TLRPC.TL_messages_getBotCallbackAnswer req=new TLRPC.TL_messages_getBotCallbackAnswer();
    req.peer=MessagesController.getInstance(currentAccount).getInputPeer(lowerId);
    req.msg_id=msgId;
    req.game=false;
    if (data != null) {
      req.flags|=1;
      req.data=data;
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> waitingForCallback.remove(key)),ConnectionsManager.RequestFlagFailOnServerErrors);
    MessagesController.getInstance(currentAccount).markDialogAsRead(dialogId,msgId,msgId,0,false,0,true);
  }
);
}
