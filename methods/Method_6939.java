protected void updateInterfaceWithMessages(final long uid,final ArrayList<MessageObject> messages,boolean isBroadcast){
  if (messages == null || messages.isEmpty()) {
    return;
  }
  boolean isEncryptedChat=((int)uid) == 0;
  MessageObject lastMessage=null;
  int channelId=0;
  boolean updateRating=false;
  boolean hasNotOutMessage=false;
  for (int a=0; a < messages.size(); a++) {
    MessageObject message=messages.get(a);
    if (lastMessage == null || (!isEncryptedChat && message.getId() > lastMessage.getId() || (isEncryptedChat || message.getId() < 0 && lastMessage.getId() < 0) && message.getId() < lastMessage.getId()) || message.messageOwner.date > lastMessage.messageOwner.date) {
      lastMessage=message;
      if (message.messageOwner.to_id.channel_id != 0) {
        channelId=message.messageOwner.to_id.channel_id;
      }
    }
    if (!hasNotOutMessage && !message.isOut()) {
      hasNotOutMessage=true;
    }
    if (message.isOut() && !message.isSending() && !message.isForwarded()) {
      if (message.isNewGif()) {
        DataQuery.getInstance(currentAccount).addRecentGif(message.messageOwner.media.document,message.messageOwner.date);
      }
 else       if (message.isSticker()) {
        DataQuery.getInstance(currentAccount).addRecentSticker(DataQuery.TYPE_IMAGE,message,message.messageOwner.media.document,message.messageOwner.date,false);
      }
    }
    if (message.isOut() && message.isSent()) {
      updateRating=true;
    }
  }
  DataQuery.getInstance(currentAccount).loadReplyMessagesForMessages(messages,uid);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didReceiveNewMessages,uid,messages);
  if (lastMessage == null) {
    return;
  }
  TLRPC.TL_dialog dialog=(TLRPC.TL_dialog)dialogs_dict.get(uid);
  if (lastMessage.messageOwner.action instanceof TLRPC.TL_messageActionChatMigrateTo) {
    if (dialog != null) {
      allDialogs.remove(dialog);
      dialogsServerOnly.remove(dialog);
      dialogsCanAddUsers.remove(dialog);
      dialogsChannelsOnly.remove(dialog);
      dialogsGroupsOnly.remove(dialog);
      dialogsUsersOnly.remove(dialog);
      dialogsForward.remove(dialog);
      dialogs_dict.remove(dialog.id);
      dialogs_read_inbox_max.remove(dialog.id);
      dialogs_read_outbox_max.remove(dialog.id);
      int offset=nextDialogsCacheOffset.get(dialog.folder_id,0);
      if (offset > 0) {
        nextDialogsCacheOffset.put(dialog.folder_id,offset - 1);
      }
      dialogMessage.remove(dialog.id);
      ArrayList<TLRPC.Dialog> dialogs=dialogsByFolder.get(dialog.folder_id);
      if (dialogs != null) {
        dialogs.remove(dialog);
      }
      MessageObject object=dialogMessagesByIds.get(dialog.top_message);
      dialogMessagesByIds.remove(dialog.top_message);
      if (object != null && object.messageOwner.random_id != 0) {
        dialogMessagesByRandomIds.remove(object.messageOwner.random_id);
      }
      dialog.top_message=0;
      NotificationsController.getInstance(currentAccount).removeNotificationsForDialog(dialog.id);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch);
    }
    return;
  }
  boolean changed=false;
  if (dialog == null) {
    if (!isBroadcast) {
      TLRPC.Chat chat=getChat(channelId);
      if (channelId != 0 && chat == null || chat != null && chat.left) {
        return;
      }
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("not found dialog with id " + uid + " dictCount = " + dialogs_dict.size() + " allCount = " + allDialogs.size());
      }
      dialog=new TLRPC.TL_dialog();
      dialog.id=uid;
      dialog.unread_count=0;
      dialog.top_message=lastMessage.getId();
      dialog.last_message_date=lastMessage.messageOwner.date;
      dialog.flags=ChatObject.isChannel(chat) ? 1 : 0;
      dialogs_dict.put(uid,dialog);
      allDialogs.add(dialog);
      dialogMessage.put(uid,lastMessage);
      if (lastMessage.messageOwner.to_id.channel_id == 0) {
        dialogMessagesByIds.put(lastMessage.getId(),lastMessage);
        if (lastMessage.messageOwner.random_id != 0) {
          dialogMessagesByRandomIds.put(lastMessage.messageOwner.random_id,lastMessage);
        }
      }
      changed=true;
      TLRPC.Dialog dialogFinal=dialog;
      MessagesStorage.getInstance(currentAccount).getDialogFolderId(uid,param -> {
        if (param != -1) {
          if (param != 0) {
            dialogFinal.folder_id=param;
            sortDialogs(null);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
          }
        }
 else {
          int lowerId=(int)uid;
          if (lowerId != 0) {
            loadUnknownDialog(getInputPeer(lowerId),0);
          }
        }
      }
);
    }
  }
 else {
    if (hasNotOutMessage && dialog.folder_id == 1 && !isDialogMuted(dialog.id)) {
      dialog.folder_id=0;
      dialog.pinned=false;
      dialog.pinnedNum=0;
      MessagesStorage.getInstance(currentAccount).setDialogsFolderId(null,null,dialog.id,0);
      changed=true;
    }
    if ((dialog.top_message > 0 && lastMessage.getId() > 0 && lastMessage.getId() > dialog.top_message) || (dialog.top_message < 0 && lastMessage.getId() < 0 && lastMessage.getId() < dialog.top_message) || dialogMessage.indexOfKey(uid) < 0 || dialog.top_message < 0 || dialog.last_message_date <= lastMessage.messageOwner.date) {
      MessageObject object=dialogMessagesByIds.get(dialog.top_message);
      dialogMessagesByIds.remove(dialog.top_message);
      if (object != null && object.messageOwner.random_id != 0) {
        dialogMessagesByRandomIds.remove(object.messageOwner.random_id);
      }
      dialog.top_message=lastMessage.getId();
      if (!isBroadcast) {
        dialog.last_message_date=lastMessage.messageOwner.date;
        changed=true;
      }
      dialogMessage.put(uid,lastMessage);
      if (lastMessage.messageOwner.to_id.channel_id == 0) {
        dialogMessagesByIds.put(lastMessage.getId(),lastMessage);
        if (lastMessage.messageOwner.random_id != 0) {
          dialogMessagesByRandomIds.put(lastMessage.messageOwner.random_id,lastMessage);
        }
      }
    }
  }
  if (changed) {
    sortDialogs(null);
  }
  if (updateRating) {
    DataQuery.getInstance(currentAccount).increasePeerRaiting(uid);
  }
}
