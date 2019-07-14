private void reloadMessages(final ArrayList<Integer> mids,final long dialog_id){
  if (mids.isEmpty()) {
    return;
  }
  TLObject request;
  final ArrayList<Integer> result=new ArrayList<>();
  final TLRPC.Chat chat=ChatObject.getChatByDialog(dialog_id,currentAccount);
  if (ChatObject.isChannel(chat)) {
    TLRPC.TL_channels_getMessages req=new TLRPC.TL_channels_getMessages();
    req.channel=getInputChannel(chat);
    req.id=result;
    request=req;
  }
 else {
    TLRPC.TL_messages_getMessages req=new TLRPC.TL_messages_getMessages();
    req.id=result;
    request=req;
  }
  ArrayList<Integer> arrayList=reloadingMessages.get(dialog_id);
  for (int a=0; a < mids.size(); a++) {
    Integer mid=mids.get(a);
    if (arrayList != null && arrayList.contains(mid)) {
      continue;
    }
    result.add(mid);
  }
  if (result.isEmpty()) {
    return;
  }
  if (arrayList == null) {
    arrayList=new ArrayList<>();
    reloadingMessages.put(dialog_id,arrayList);
  }
  arrayList.addAll(result);
  ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
    if (error == null) {
      TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
      final SparseArray<TLRPC.User> usersLocal=new SparseArray<>();
      for (int a=0; a < messagesRes.users.size(); a++) {
        TLRPC.User u=messagesRes.users.get(a);
        usersLocal.put(u.id,u);
      }
      final SparseArray<TLRPC.Chat> chatsLocal=new SparseArray<>();
      for (int a=0; a < messagesRes.chats.size(); a++) {
        TLRPC.Chat c=messagesRes.chats.get(a);
        chatsLocal.put(c.id,c);
      }
      Integer inboxValue=dialogs_read_inbox_max.get(dialog_id);
      if (inboxValue == null) {
        inboxValue=MessagesStorage.getInstance(currentAccount).getDialogReadMax(false,dialog_id);
        dialogs_read_inbox_max.put(dialog_id,inboxValue);
      }
      Integer outboxValue=dialogs_read_outbox_max.get(dialog_id);
      if (outboxValue == null) {
        outboxValue=MessagesStorage.getInstance(currentAccount).getDialogReadMax(true,dialog_id);
        dialogs_read_outbox_max.put(dialog_id,outboxValue);
      }
      final ArrayList<MessageObject> objects=new ArrayList<>();
      for (int a=0; a < messagesRes.messages.size(); a++) {
        TLRPC.Message message=messagesRes.messages.get(a);
        if (chat != null && chat.megagroup) {
          message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
        }
        message.dialog_id=dialog_id;
        message.unread=(message.out ? outboxValue : inboxValue) < message.id;
        objects.add(new MessageObject(currentAccount,message,usersLocal,chatsLocal,true));
      }
      ImageLoader.saveMessagesThumbs(messagesRes.messages);
      MessagesStorage.getInstance(currentAccount).putMessages(messagesRes,dialog_id,-1,0,false);
      AndroidUtilities.runOnUIThread(() -> {
        ArrayList<Integer> arrayList1=reloadingMessages.get(dialog_id);
        if (arrayList1 != null) {
          arrayList1.removeAll(result);
          if (arrayList1.isEmpty()) {
            reloadingMessages.remove(dialog_id);
          }
        }
        MessageObject dialogObj=dialogMessage.get(dialog_id);
        if (dialogObj != null) {
          for (int a=0; a < objects.size(); a++) {
            MessageObject obj=objects.get(a);
            if (dialogObj != null && dialogObj.getId() == obj.getId()) {
              dialogMessage.put(dialog_id,obj);
              if (obj.messageOwner.to_id.channel_id == 0) {
                MessageObject obj2=dialogMessagesByIds.get(obj.getId());
                dialogMessagesByIds.remove(obj.getId());
                if (obj2 != null) {
                  dialogMessagesByIds.put(obj2.getId(),obj2);
                }
              }
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
              break;
            }
          }
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,dialog_id,objects);
      }
);
    }
  }
);
}
