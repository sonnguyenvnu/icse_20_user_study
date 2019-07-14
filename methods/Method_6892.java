public void processDialogsUpdate(final TLRPC.messages_Dialogs dialogsRes,ArrayList<TLRPC.EncryptedChat> encChats){
  Utilities.stageQueue.postRunnable(() -> {
    final LongSparseArray<TLRPC.Dialog> new_dialogs_dict=new LongSparseArray<>();
    final LongSparseArray<MessageObject> new_dialogMessage=new LongSparseArray<>();
    final SparseArray<TLRPC.User> usersDict=new SparseArray<>(dialogsRes.users.size());
    final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>(dialogsRes.chats.size());
    final LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>();
    for (int a=0; a < dialogsRes.users.size(); a++) {
      TLRPC.User u=dialogsRes.users.get(a);
      usersDict.put(u.id,u);
    }
    for (int a=0; a < dialogsRes.chats.size(); a++) {
      TLRPC.Chat c=dialogsRes.chats.get(a);
      chatsDict.put(c.id,c);
    }
    for (int a=0; a < dialogsRes.messages.size(); a++) {
      TLRPC.Message message=dialogsRes.messages.get(a);
      if (proxyDialogId == 0 || proxyDialogId != message.dialog_id) {
        if (message.to_id.channel_id != 0) {
          TLRPC.Chat chat=chatsDict.get(message.to_id.channel_id);
          if (chat != null && chat.left) {
            continue;
          }
        }
 else         if (message.to_id.chat_id != 0) {
          TLRPC.Chat chat=chatsDict.get(message.to_id.chat_id);
          if (chat != null && chat.migrated_to != null) {
            continue;
          }
        }
      }
      MessageObject messageObject=new MessageObject(currentAccount,message,usersDict,chatsDict,false);
      new_dialogMessage.put(messageObject.getDialogId(),messageObject);
    }
    for (int a=0; a < dialogsRes.dialogs.size(); a++) {
      TLRPC.Dialog d=dialogsRes.dialogs.get(a);
      DialogObject.initDialog(d);
      if (proxyDialogId == 0 || proxyDialogId != d.id) {
        if (DialogObject.isChannel(d)) {
          TLRPC.Chat chat=chatsDict.get(-(int)d.id);
          if (chat != null && chat.left) {
            continue;
          }
        }
 else         if ((int)d.id < 0) {
          TLRPC.Chat chat=chatsDict.get(-(int)d.id);
          if (chat != null && chat.migrated_to != null) {
            continue;
          }
        }
      }
      if (d.last_message_date == 0) {
        MessageObject mess=new_dialogMessage.get(d.id);
        if (mess != null) {
          d.last_message_date=mess.messageOwner.date;
        }
      }
      new_dialogs_dict.put(d.id,d);
      dialogsToUpdate.put(d.id,d.unread_count);
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
    AndroidUtilities.runOnUIThread(() -> {
      putUsers(dialogsRes.users,true);
      putChats(dialogsRes.chats,true);
      for (int a=0; a < new_dialogs_dict.size(); a++) {
        long key=new_dialogs_dict.keyAt(a);
        TLRPC.Dialog value=new_dialogs_dict.valueAt(a);
        TLRPC.Dialog currentDialog=dialogs_dict.get(key);
        if (currentDialog == null) {
          int offset=nextDialogsCacheOffset.get(value.folder_id,0) + 1;
          nextDialogsCacheOffset.put(value.folder_id,offset);
          dialogs_dict.put(key,value);
          MessageObject messageObject=new_dialogMessage.get(value.id);
          dialogMessage.put(key,messageObject);
          if (messageObject != null && messageObject.messageOwner.to_id.channel_id == 0) {
            dialogMessagesByIds.put(messageObject.getId(),messageObject);
            if (messageObject.messageOwner.random_id != 0) {
              dialogMessagesByRandomIds.put(messageObject.messageOwner.random_id,messageObject);
            }
          }
        }
 else {
          currentDialog.unread_count=value.unread_count;
          if (currentDialog.unread_mentions_count != value.unread_mentions_count) {
            currentDialog.unread_mentions_count=value.unread_mentions_count;
            if (createdDialogMainThreadIds.contains(currentDialog.id)) {
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateMentionsCount,currentDialog.id,currentDialog.unread_mentions_count);
            }
          }
          MessageObject oldMsg=dialogMessage.get(key);
          if (oldMsg == null || currentDialog.top_message > 0) {
            if (oldMsg != null && oldMsg.deleted || value.top_message > currentDialog.top_message) {
              dialogs_dict.put(key,value);
              MessageObject messageObject=new_dialogMessage.get(value.id);
              dialogMessage.put(key,messageObject);
              if (messageObject != null && messageObject.messageOwner.to_id.channel_id == 0) {
                dialogMessagesByIds.put(messageObject.getId(),messageObject);
                if (messageObject.messageOwner.random_id != 0) {
                  dialogMessagesByRandomIds.put(messageObject.messageOwner.random_id,messageObject);
                }
              }
              if (oldMsg != null) {
                dialogMessagesByIds.remove(oldMsg.getId());
                if (oldMsg.messageOwner.random_id != 0) {
                  dialogMessagesByRandomIds.remove(oldMsg.messageOwner.random_id);
                }
              }
              if (messageObject == null) {
                checkLastDialogMessage(value,null,0);
              }
            }
          }
 else {
            MessageObject newMsg=new_dialogMessage.get(value.id);
            if (oldMsg.deleted || newMsg == null || newMsg.messageOwner.date > oldMsg.messageOwner.date) {
              dialogs_dict.put(key,value);
              dialogMessage.put(key,newMsg);
              if (newMsg != null && newMsg.messageOwner.to_id.channel_id == 0) {
                dialogMessagesByIds.put(newMsg.getId(),newMsg);
                if (newMsg.messageOwner.random_id != 0) {
                  dialogMessagesByRandomIds.put(newMsg.messageOwner.random_id,newMsg);
                }
              }
              dialogMessagesByIds.remove(oldMsg.getId());
              if (oldMsg.messageOwner.random_id != 0) {
                dialogMessagesByRandomIds.remove(oldMsg.messageOwner.random_id);
              }
            }
          }
        }
      }
      allDialogs.clear();
      for (int a=0, size=dialogs_dict.size(); a < size; a++) {
        allDialogs.add(dialogs_dict.valueAt(a));
      }
      sortDialogs(null);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
      NotificationsController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate);
    }
);
  }
);
}
