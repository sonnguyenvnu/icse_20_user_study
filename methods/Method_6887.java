public void processLoadedDialogs(final TLRPC.messages_Dialogs dialogsRes,final ArrayList<TLRPC.EncryptedChat> encChats,final int folderId,final int offset,final int count,final int loadType,final boolean resetEnd,final boolean migrate,final boolean fromCache){
  Utilities.stageQueue.postRunnable(() -> {
    if (!firstGettingTask) {
      getNewDeleteTask(null,0);
      firstGettingTask=true;
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("loaded folderId " + folderId + " loadType " + loadType + " count " + dialogsRes.dialogs.size());
    }
    int[] dialogsLoadOffset=UserConfig.getInstance(currentAccount).getDialogLoadOffsets(folderId);
    if (loadType == DIALOGS_LOAD_TYPE_CACHE && dialogsRes.dialogs.size() == 0) {
      AndroidUtilities.runOnUIThread(() -> {
        putUsers(dialogsRes.users,true);
        loadingDialogs.put(folderId,false);
        if (resetEnd) {
          dialogsEndReached.put(folderId,false);
          serverDialogsEndReached.put(folderId,false);
        }
 else         if (dialogsLoadOffset[UserConfig.i_dialogsLoadOffsetId] == Integer.MAX_VALUE) {
          dialogsEndReached.put(folderId,true);
          serverDialogsEndReached.put(folderId,true);
        }
 else {
          loadDialogs(folderId,0,count,false);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
      }
);
      return;
    }
    final LongSparseArray<TLRPC.Dialog> new_dialogs_dict=new LongSparseArray<>();
    final SparseArray<TLRPC.EncryptedChat> enc_chats_dict;
    final LongSparseArray<MessageObject> new_dialogMessage=new LongSparseArray<>();
    final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
    final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
    for (int a=0; a < dialogsRes.users.size(); a++) {
      TLRPC.User u=dialogsRes.users.get(a);
      usersDict.put(u.id,u);
    }
    for (int a=0; a < dialogsRes.chats.size(); a++) {
      TLRPC.Chat c=dialogsRes.chats.get(a);
      chatsDict.put(c.id,c);
    }
    if (encChats != null) {
      enc_chats_dict=new SparseArray<>();
      for (int a=0, N=encChats.size(); a < N; a++) {
        TLRPC.EncryptedChat encryptedChat=encChats.get(a);
        enc_chats_dict.put(encryptedChat.id,encryptedChat);
      }
    }
 else {
      enc_chats_dict=null;
    }
    if (loadType == DIALOGS_LOAD_TYPE_CACHE) {
      nextDialogsCacheOffset.put(folderId,offset + count);
    }
    TLRPC.Message lastMessage=null;
    for (int a=0; a < dialogsRes.messages.size(); a++) {
      TLRPC.Message message=dialogsRes.messages.get(a);
      if (lastMessage == null || message.date < lastMessage.date) {
        lastMessage=message;
      }
      if (message.to_id.channel_id != 0) {
        TLRPC.Chat chat=chatsDict.get(message.to_id.channel_id);
        if (chat != null && chat.left && (proxyDialogId == 0 || proxyDialogId != -chat.id)) {
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
    if (!fromCache && !migrate && dialogsLoadOffset[UserConfig.i_dialogsLoadOffsetId] != -1 && loadType == 0) {
      int totalDialogsLoadCount=UserConfig.getInstance(currentAccount).getTotalDialogsCount(folderId);
      int dialogsLoadOffsetId;
      int dialogsLoadOffsetDate=0;
      int dialogsLoadOffsetChannelId=0;
      int dialogsLoadOffsetChatId=0;
      int dialogsLoadOffsetUserId=0;
      long dialogsLoadOffsetAccess=0;
      if (lastMessage != null && lastMessage.id != dialogsLoadOffset[UserConfig.i_dialogsLoadOffsetId]) {
        totalDialogsLoadCount+=dialogsRes.dialogs.size();
        dialogsLoadOffsetId=lastMessage.id;
        dialogsLoadOffsetDate=lastMessage.date;
        if (lastMessage.to_id.channel_id != 0) {
          dialogsLoadOffsetChannelId=lastMessage.to_id.channel_id;
          dialogsLoadOffsetChatId=0;
          dialogsLoadOffsetUserId=0;
          for (int a=0; a < dialogsRes.chats.size(); a++) {
            TLRPC.Chat chat=dialogsRes.chats.get(a);
            if (chat.id == dialogsLoadOffsetChannelId) {
              dialogsLoadOffsetAccess=chat.access_hash;
              break;
            }
          }
        }
 else         if (lastMessage.to_id.chat_id != 0) {
          dialogsLoadOffsetChatId=lastMessage.to_id.chat_id;
          dialogsLoadOffsetChannelId=0;
          dialogsLoadOffsetUserId=0;
          for (int a=0; a < dialogsRes.chats.size(); a++) {
            TLRPC.Chat chat=dialogsRes.chats.get(a);
            if (chat.id == dialogsLoadOffsetChatId) {
              dialogsLoadOffsetAccess=chat.access_hash;
              break;
            }
          }
        }
 else         if (lastMessage.to_id.user_id != 0) {
          dialogsLoadOffsetUserId=lastMessage.to_id.user_id;
          dialogsLoadOffsetChatId=0;
          dialogsLoadOffsetChannelId=0;
          for (int a=0; a < dialogsRes.users.size(); a++) {
            TLRPC.User user=dialogsRes.users.get(a);
            if (user.id == dialogsLoadOffsetUserId) {
              dialogsLoadOffsetAccess=user.access_hash;
              break;
            }
          }
        }
      }
 else {
        dialogsLoadOffsetId=Integer.MAX_VALUE;
      }
      UserConfig.getInstance(currentAccount).setDialogsLoadOffset(folderId,dialogsLoadOffsetId,dialogsLoadOffsetDate,dialogsLoadOffsetUserId,dialogsLoadOffsetChatId,dialogsLoadOffsetChannelId,dialogsLoadOffsetAccess);
      UserConfig.getInstance(currentAccount).setTotalDialogsCount(folderId,totalDialogsLoadCount);
      UserConfig.getInstance(currentAccount).saveConfig(false);
    }
    final ArrayList<TLRPC.Dialog> dialogsToReload=new ArrayList<>();
    for (int a=0; a < dialogsRes.dialogs.size(); a++) {
      TLRPC.Dialog d=dialogsRes.dialogs.get(a);
      DialogObject.initDialog(d);
      if (d.id == 0) {
        continue;
      }
      int lower_id=(int)d.id;
      int high_id=(int)(d.id >> 32);
      if (lower_id == 0 && enc_chats_dict != null) {
        if (enc_chats_dict.get(high_id) == null) {
          continue;
        }
      }
      if (proxyDialogId != 0 && proxyDialogId == d.id) {
        proxyDialog=d;
      }
      if (d.last_message_date == 0) {
        MessageObject mess=new_dialogMessage.get(d.id);
        if (mess != null) {
          d.last_message_date=mess.messageOwner.date;
        }
      }
      boolean allowCheck=true;
      if (DialogObject.isChannel(d)) {
        TLRPC.Chat chat=chatsDict.get(-(int)d.id);
        if (chat != null) {
          if (!chat.megagroup) {
            allowCheck=false;
          }
          if (chat.left && (proxyDialogId == 0 || proxyDialogId != d.id)) {
            continue;
          }
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
      if (allowCheck && loadType == DIALOGS_LOAD_TYPE_CACHE && (d.read_outbox_max_id == 0 || d.read_inbox_max_id == 0) && d.top_message != 0) {
        dialogsToReload.add(d);
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
    if (loadType != DIALOGS_LOAD_TYPE_CACHE) {
      ImageLoader.saveMessagesThumbs(dialogsRes.messages);
      for (int a=0; a < dialogsRes.messages.size(); a++) {
        TLRPC.Message message=dialogsRes.messages.get(a);
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
      MessagesStorage.getInstance(currentAccount).putDialogs(dialogsRes,0);
    }
    if (loadType == DIALOGS_LOAD_TYPE_CHANNEL) {
      TLRPC.Chat chat=dialogsRes.chats.get(0);
      getChannelDifference(chat.id);
      checkChannelInviter(chat.id);
    }
    AndroidUtilities.runOnUIThread(() -> {
      if (loadType != DIALOGS_LOAD_TYPE_CACHE) {
        applyDialogsNotificationsSettings(dialogsRes.dialogs);
        if (!UserConfig.getInstance(currentAccount).draftsLoaded) {
          DataQuery.getInstance(currentAccount).loadDrafts();
        }
      }
      putUsers(dialogsRes.users,loadType == DIALOGS_LOAD_TYPE_CACHE);
      putChats(dialogsRes.chats,loadType == DIALOGS_LOAD_TYPE_CACHE);
      if (encChats != null) {
        for (int a=0; a < encChats.size(); a++) {
          TLRPC.EncryptedChat encryptedChat=encChats.get(a);
          if (encryptedChat instanceof TLRPC.TL_encryptedChat && AndroidUtilities.getMyLayerVersion(encryptedChat.layer) < SecretChatHelper.CURRENT_SECRET_CHAT_LAYER) {
            SecretChatHelper.getInstance(currentAccount).sendNotifyLayerMessage(encryptedChat,null);
          }
          putEncryptedChat(encryptedChat,true);
        }
      }
      if (!migrate && loadType != DIALOGS_LOAD_TYPE_UNKNOWN && loadType != DIALOGS_LOAD_TYPE_CHANNEL) {
        loadingDialogs.put(folderId,false);
      }
      boolean added=false;
      dialogsLoaded=true;
      int archivedDialogsCount=0;
      int lastDialogDate=migrate && !allDialogs.isEmpty() ? allDialogs.get(allDialogs.size() - 1).last_message_date : 0;
      for (int a=0; a < new_dialogs_dict.size(); a++) {
        long key=new_dialogs_dict.keyAt(a);
        TLRPC.Dialog value=new_dialogs_dict.valueAt(a);
        TLRPC.Dialog currentDialog;
        if (loadType != DIALOGS_LOAD_TYPE_UNKNOWN) {
          currentDialog=dialogs_dict.get(key);
        }
 else {
          currentDialog=null;
        }
        if (migrate && currentDialog != null) {
          currentDialog.folder_id=value.folder_id;
        }
        if (migrate && lastDialogDate != 0 && value.last_message_date < lastDialogDate) {
          continue;
        }
        if (loadType != DIALOGS_LOAD_TYPE_CACHE && value.draft instanceof TLRPC.TL_draftMessage) {
          DataQuery.getInstance(currentAccount).saveDraft(value.id,value.draft,null,false);
        }
        if (value.folder_id != folderId) {
          archivedDialogsCount++;
        }
        if (currentDialog == null) {
          added=true;
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
          if (loadType != DIALOGS_LOAD_TYPE_CACHE) {
            currentDialog.notify_settings=value.notify_settings;
          }
          currentDialog.pinned=value.pinned;
          currentDialog.pinnedNum=value.pinnedNum;
          MessageObject oldMsg=dialogMessage.get(key);
          if (oldMsg != null && oldMsg.deleted || oldMsg == null || currentDialog.top_message > 0) {
            if (value.top_message >= currentDialog.top_message) {
              dialogs_dict.put(key,value);
              MessageObject messageObject=new_dialogMessage.get(value.id);
              dialogMessage.put(key,messageObject);
              if (messageObject != null && messageObject.messageOwner.to_id.channel_id == 0) {
                dialogMessagesByIds.put(messageObject.getId(),messageObject);
                if (messageObject != null && messageObject.messageOwner.random_id != 0) {
                  dialogMessagesByRandomIds.put(messageObject.messageOwner.random_id,messageObject);
                }
              }
              if (oldMsg != null) {
                dialogMessagesByIds.remove(oldMsg.getId());
                if (oldMsg.messageOwner.random_id != 0) {
                  dialogMessagesByRandomIds.remove(oldMsg.messageOwner.random_id);
                }
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
                if (newMsg != null && newMsg.messageOwner.random_id != 0) {
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
      sortDialogs(migrate ? chatsDict : null);
      if (loadType != DIALOGS_LOAD_TYPE_CHANNEL && loadType != DIALOGS_LOAD_TYPE_UNKNOWN) {
        if (!migrate) {
          dialogsEndReached.put(folderId,(dialogsRes.dialogs.size() == 0 || dialogsRes.dialogs.size() != count) && loadType == 0);
          if (archivedDialogsCount > 0 && archivedDialogsCount < 20 && folderId == 0) {
            dialogsEndReached.put(1,true);
            int[] dialogsLoadOffsetArchived=UserConfig.getInstance(currentAccount).getDialogLoadOffsets(folderId);
            if (dialogsLoadOffsetArchived[UserConfig.i_dialogsLoadOffsetId] == Integer.MAX_VALUE) {
              serverDialogsEndReached.put(1,true);
            }
          }
          if (!fromCache) {
            serverDialogsEndReached.put(folderId,(dialogsRes.dialogs.size() == 0 || dialogsRes.dialogs.size() != count) && loadType == 0);
          }
        }
      }
      int totalDialogsLoadCount=UserConfig.getInstance(currentAccount).getTotalDialogsCount(folderId);
      int[] dialogsLoadOffset2=UserConfig.getInstance(currentAccount).getDialogLoadOffsets(folderId);
      if (!fromCache && !migrate && totalDialogsLoadCount < 400 && dialogsLoadOffset2[UserConfig.i_dialogsLoadOffsetId] != -1 && dialogsLoadOffset2[UserConfig.i_dialogsLoadOffsetId] != Integer.MAX_VALUE) {
        loadDialogs(0,100,folderId,false);
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
      if (migrate) {
        UserConfig.getInstance(currentAccount).migrateOffsetId=offset;
        UserConfig.getInstance(currentAccount).saveConfig(false);
        migratingDialogs=false;
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch);
      }
 else {
        generateUpdateMessage();
        if (!added && loadType == DIALOGS_LOAD_TYPE_CACHE) {
          loadDialogs(folderId,0,count,false);
        }
      }
      migrateDialogs(UserConfig.getInstance(currentAccount).migrateOffsetId,UserConfig.getInstance(currentAccount).migrateOffsetDate,UserConfig.getInstance(currentAccount).migrateOffsetUserId,UserConfig.getInstance(currentAccount).migrateOffsetChatId,UserConfig.getInstance(currentAccount).migrateOffsetChannelId,UserConfig.getInstance(currentAccount).migrateOffsetAccess);
      if (!dialogsToReload.isEmpty()) {
        reloadDialogsReadValue(dialogsToReload,0);
      }
      loadUnreadDialogs();
    }
);
  }
);
}
