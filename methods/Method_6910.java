public void changeChatTitle(int chat_id,String title){
  if (chat_id > 0) {
    TLObject request;
    if (ChatObject.isChannel(chat_id,currentAccount)) {
      TLRPC.TL_channels_editTitle req=new TLRPC.TL_channels_editTitle();
      req.channel=getInputChannel(chat_id);
      req.title=title;
      request=req;
    }
 else {
      TLRPC.TL_messages_editChatTitle req=new TLRPC.TL_messages_editChatTitle();
      req.chat_id=chat_id;
      req.title=title;
      request=req;
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
      if (error != null) {
        return;
      }
      processUpdates((TLRPC.Updates)response,false);
    }
,ConnectionsManager.RequestFlagInvokeAfter);
  }
 else {
    TLRPC.Chat chat=getChat(chat_id);
    chat.title=title;
    ArrayList<TLRPC.Chat> chatArrayList=new ArrayList<>();
    chatArrayList.add(chat);
    MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,chatArrayList,true,true);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_CHAT_NAME);
  }
}
