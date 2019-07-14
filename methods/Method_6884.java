private void resetDialogs(boolean query,final int seq,final int newPts,final int date,final int qts){
  if (query) {
    if (resetingDialogs) {
      return;
    }
    UserConfig.getInstance(currentAccount).setPinnedDialogsLoaded(1,false);
    resetingDialogs=true;
    TLRPC.TL_messages_getPinnedDialogs req=new TLRPC.TL_messages_getPinnedDialogs();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (response != null) {
        resetDialogsPinned=(TLRPC.TL_messages_peerDialogs)response;
        for (int a=0; a < resetDialogsPinned.dialogs.size(); a++) {
          TLRPC.Dialog d=resetDialogsPinned.dialogs.get(a);
          d.pinned=true;
        }
        resetDialogs(false,seq,newPts,date,qts);
      }
    }
);
    TLRPC.TL_messages_getDialogs req2=new TLRPC.TL_messages_getDialogs();
    req2.limit=100;
    req2.exclude_pinned=true;
    req2.offset_peer=new TLRPC.TL_inputPeerEmpty();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req2,(response,error) -> {
      if (error == null) {
        resetDialogsAll=(TLRPC.messages_Dialogs)response;
        resetDialogs(false,seq,newPts,date,qts);
      }
    }
);
  }
 else   if (resetDialogsPinned != null && resetDialogsAll != null) {
    int messagesCount=resetDialogsAll.messages.size();
    int dialogsCount=resetDialogsAll.dialogs.size();
    fetchFolderInLoadedPinnedDialogs(resetDialogsPinned);
    resetDialogsAll.dialogs.addAll(resetDialogsPinned.dialogs);
    resetDialogsAll.messages.addAll(resetDialogsPinned.messages);
    resetDialogsAll.users.addAll(resetDialogsPinned.users);
    resetDialogsAll.chats.addAll(resetDialogsPinned.chats);
    final LongSparseArray<TLRPC.Dialog> new_dialogs_dict=new LongSparseArray<>();
    final LongSparseArray<MessageObject> new_dialogMessage=new LongSparseArray<>();
    final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
    final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
    for (int a=0; a < resetDialogsAll.users.size(); a++) {
      TLRPC.User u=resetDialogsAll.users.get(a);
      usersDict.put(u.id,u);
    }
    for (int a=0; a < resetDialogsAll.chats.size(); a++) {
      TLRPC.Chat c=resetDialogsAll.chats.get(a);
      chatsDict.put(c.id,c);
    }
    TLRPC.Message lastMessage=null;
    for (int a=0; a < resetDialogsAll.messages.size(); a++) {
      TLRPC.Message message=resetDialogsAll.messages.get(a);
      if (a < messagesCount) {
        if (lastMessage == null || message.date < lastMessage.date) {
          lastMessage=message;
        }
      }
      if (message.to_id.channel_id != 0) {
        TLRPC.Chat chat=chatsDict.get(message.to_id.channel_id);
        if (chat != null && chat.left) {
          continue;
        }
        if (chat != null && chat.megagroup) {
          message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
        }
      }
 else       if (message.to_id.chat_id != 0) {
        TLRPC.Chat chat=chatsDict.get(message.to_id.chat_id);
        if (chat != null && chat.migrated_to != null) {
          continue;
        }
      }
      MessageObject messageObject=new MessageObject(currentAccount,message,usersDict,chatsDict,false);
      new_dialogMessage.put(messageObject.getDialogId(),messageObject);
    }
    for (int a=0; a < resetDialogsAll.dialogs.size(); a++) {
      TLRPC.Dialog d=resetDialogsAll.dialogs.get(a);
      DialogObject.initDialog(d);
      if (d.id == 0) {
        continue;
      }
      if (d.last_message_date == 0) {
        MessageObject mess=new_dialogMessage.get(d.id);
        if (mess != null) {
          d.last_message_date=mess.messageOwner.date;
        }
      }
      if (DialogObject.isChannel(d)) {
        TLRPC.Chat chat=chatsDict.get(-(int)d.id);
        if (chat != null && chat.left) {
          continue;
        }
        channelsPts.put(-(int)d.id,d.pts);
      }
 else       if ((int)d.id < 0) {
        TLRPC.Chat chat=chatsDict.get(-(int)d.id);
        if (chat != null && chat.migrated_to != null) {
          continue;
        }
      }
      new_dialogs_dict.put(d.id,d);
      Integer value=dialogs_read_inbox_max.get(d.id);
      if (value == null) {
        value=0;
      }
      dialogs_read_inbox_max.put(d.id,Math.max(value,d.read_inbox_max_id));
      value=dialogs_read_outbox_max.get(d.id);
      if (value == null) {
        value=0;
      }
      dialogs_read_outbox_max.put(d.id,Math.max(value,d.read_outbox_max_id));
    }
    ImageLoader.saveMessagesThumbs(resetDialogsAll.messages);
    for (int a=0; a < resetDialogsAll.messages.size(); a++) {
      TLRPC.Message message=resetDialogsAll.messages.get(a);
      if (message.action instanceof TLRPC.TL_messageActionChatDeleteUser) {
        TLRPC.User user=usersDict.get(message.action.user_id);
        if (user != null && user.bot) {
          message.reply_markup=new TLRPC.TL_replyKeyboardHide();
          message.flags|=64;
        }
      }
      if (message.action instanceof TLRPC.TL_messageActionChatMigrateTo || message.action instanceof TLRPC.TL_messageActionChannelCreate) {
        message.unread=false;
        message.media_unread=false;
      }
 else {
        ConcurrentHashMap<Long,Integer> read_max=message.out ? dialogs_read_outbox_max : dialogs_read_inbox_max;
        Integer value=read_max.get(message.dialog_id);
        if (value == null) {
          value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(message.out,message.dialog_id);
          read_max.put(message.dialog_id,value);
        }
        message.unread=value < message.id;
      }
    }
    MessagesStorage.getInstance(currentAccount).resetDialogs(resetDialogsAll,messagesCount,seq,newPts,date,qts,new_dialogs_dict,new_dialogMessage,lastMessage,dialogsCount);
    resetDialogsPinned=null;
    resetDialogsAll=null;
  }
}
