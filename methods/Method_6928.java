public void loadPinnedDialogs(final int folderId,final long newDialogId,final ArrayList<Long> order){
  if (loadingPinnedDialogs.indexOfKey(folderId) >= 0 || UserConfig.getInstance(currentAccount).isPinnedDialogsLoaded(folderId)) {
    return;
  }
  loadingPinnedDialogs.put(folderId,1);
  TLRPC.TL_messages_getPinnedDialogs req=new TLRPC.TL_messages_getPinnedDialogs();
  req.folder_id=folderId;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      final TLRPC.TL_messages_peerDialogs res=(TLRPC.TL_messages_peerDialogs)response;
      ArrayList<TLRPC.Dialog> newPinnedDialogs=new ArrayList<>(res.dialogs);
      fetchFolderInLoadedPinnedDialogs(res);
      final TLRPC.TL_messages_dialogs toCache=new TLRPC.TL_messages_dialogs();
      toCache.users.addAll(res.users);
      toCache.chats.addAll(res.chats);
      toCache.dialogs.addAll(res.dialogs);
      toCache.messages.addAll(res.messages);
      final LongSparseArray<MessageObject> new_dialogMessage=new LongSparseArray<>();
      final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
      final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
      for (int a=0; a < res.users.size(); a++) {
        TLRPC.User u=res.users.get(a);
        usersDict.put(u.id,u);
      }
      for (int a=0; a < res.chats.size(); a++) {
        TLRPC.Chat c=res.chats.get(a);
        chatsDict.put(c.id,c);
      }
      for (int a=0; a < res.messages.size(); a++) {
        TLRPC.Message message=res.messages.get(a);
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
        MessageObject messageObject=new MessageObject(currentAccount,message,usersDict,chatsDict,false);
        new_dialogMessage.put(messageObject.getDialogId(),messageObject);
      }
      boolean firstIsFolder=!newPinnedDialogs.isEmpty() && newPinnedDialogs.get(0) instanceof TLRPC.TL_dialogFolder;
      for (int a=0, N=newPinnedDialogs.size(); a < N; a++) {
        TLRPC.Dialog d=newPinnedDialogs.get(a);
        d.pinned=true;
        DialogObject.initDialog(d);
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
        if (d.last_message_date == 0) {
          MessageObject mess=new_dialogMessage.get(d.id);
          if (mess != null) {
            d.last_message_date=mess.messageOwner.date;
          }
        }
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
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
        loadingPinnedDialogs.delete(folderId);
        applyDialogsNotificationsSettings(newPinnedDialogs);
        boolean changed=false;
        boolean added=false;
        int maxPinnedNum=0;
        ArrayList<TLRPC.Dialog> dialogs=getDialogs(folderId);
        int pinnedNum=firstIsFolder ? 1 : 0;
        for (int a=0; a < dialogs.size(); a++) {
          TLRPC.Dialog dialog=dialogs.get(a);
          if (dialog instanceof TLRPC.TL_dialogFolder) {
            continue;
          }
          if ((int)dialog.id == 0) {
            if (pinnedNum < newPinnedDialogs.size()) {
              newPinnedDialogs.add(pinnedNum,dialog);
            }
 else {
              newPinnedDialogs.add(dialog);
            }
            pinnedNum++;
            continue;
          }
          if (!dialog.pinned) {
            break;
          }
          maxPinnedNum=Math.max(dialog.pinnedNum,maxPinnedNum);
          dialog.pinned=false;
          dialog.pinnedNum=0;
          changed=true;
          pinnedNum++;
        }
        ArrayList<Long> pinnedDialogs=new ArrayList<>();
        if (!newPinnedDialogs.isEmpty()) {
          putUsers(res.users,false);
          putChats(res.chats,false);
          for (int a=0, N=newPinnedDialogs.size(); a < N; a++) {
            TLRPC.Dialog dialog=newPinnedDialogs.get(a);
            dialog.pinnedNum=(N - a) + maxPinnedNum;
            pinnedDialogs.add(dialog.id);
            TLRPC.Dialog d=dialogs_dict.get(dialog.id);
            if (d != null) {
              d.pinned=true;
              d.pinnedNum=dialog.pinnedNum;
              MessagesStorage.getInstance(currentAccount).setDialogPinned(dialog.id,dialog.pinnedNum);
            }
 else {
              added=true;
              dialogs_dict.put(dialog.id,dialog);
              MessageObject messageObject=new_dialogMessage.get(dialog.id);
              dialogMessage.put(dialog.id,messageObject);
              if (messageObject != null && messageObject.messageOwner.to_id.channel_id == 0) {
                dialogMessagesByIds.put(messageObject.getId(),messageObject);
                if (messageObject.messageOwner.random_id != 0) {
                  dialogMessagesByRandomIds.put(messageObject.messageOwner.random_id,messageObject);
                }
              }
            }
            changed=true;
          }
        }
        if (changed) {
          if (added) {
            allDialogs.clear();
            for (int a=0, size=dialogs_dict.size(); a < size; a++) {
              allDialogs.add(dialogs_dict.valueAt(a));
            }
          }
          sortDialogs(null);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
        }
        MessagesStorage.getInstance(currentAccount).unpinAllDialogsExceptNew(pinnedDialogs,folderId);
        MessagesStorage.getInstance(currentAccount).putDialogs(toCache,1);
        UserConfig.getInstance(currentAccount).setPinnedDialogsLoaded(folderId,true);
        UserConfig.getInstance(currentAccount).saveConfig(false);
      }
));
    }
  }
);
}
