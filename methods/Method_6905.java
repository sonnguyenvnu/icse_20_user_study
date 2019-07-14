public void updateChatAbout(int chat_id,final String about,final TLRPC.ChatFull info){
  if (info == null) {
    return;
  }
  TLRPC.TL_messages_editChatAbout req=new TLRPC.TL_messages_editChatAbout();
  req.peer=getInputPeer(-chat_id);
  req.about=about;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response instanceof TLRPC.TL_boolTrue) {
      AndroidUtilities.runOnUIThread(() -> {
        info.about=about;
        MessagesStorage.getInstance(currentAccount).updateChatInfo(info,false);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,info,0,false,null);
      }
);
    }
  }
,ConnectionsManager.RequestFlagInvokeAfter);
}
