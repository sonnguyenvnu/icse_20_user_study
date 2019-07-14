private void migrateDialogs(final int offset,final int offsetDate,final int offsetUser,final int offsetChat,final int offsetChannel,final long accessPeer){
  if (migratingDialogs || offset == -1) {
    return;
  }
  migratingDialogs=true;
  TLRPC.TL_messages_getDialogs req=new TLRPC.TL_messages_getDialogs();
  req.exclude_pinned=true;
  req.limit=100;
  req.offset_id=offset;
  req.offset_date=offsetDate;
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("start migrate with id " + offset + " date " + LocaleController.getInstance().formatterStats.format((long)offsetDate * 1000));
  }
  if (offset == 0) {
    req.offset_peer=new TLRPC.TL_inputPeerEmpty();
  }
 else {
    if (offsetChannel != 0) {
      req.offset_peer=new TLRPC.TL_inputPeerChannel();
      req.offset_peer.channel_id=offsetChannel;
    }
 else     if (offsetUser != 0) {
      req.offset_peer=new TLRPC.TL_inputPeerUser();
      req.offset_peer.user_id=offsetUser;
    }
 else {
      req.offset_peer=new TLRPC.TL_inputPeerChat();
      req.offset_peer.chat_id=offsetChat;
    }
    req.offset_peer.access_hash=accessPeer;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      final TLRPC.messages_Dialogs dialogsRes=(TLRPC.messages_Dialogs)response;
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
        try {
          int offsetId;
          int totalDialogsLoadCount=UserConfig.getInstance(currentAccount).getTotalDialogsCount(0);
          UserConfig.getInstance(currentAccount).setTotalDialogsCount(0,totalDialogsLoadCount + dialogsRes.dialogs.size());
          TLRPC.Message lastMessage=null;
          for (int a=0; a < dialogsRes.messages.size(); a++) {
            TLRPC.Message message=dialogsRes.messages.get(a);
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("search migrate id " + message.id + " date " + LocaleController.getInstance().formatterStats.format((long)message.date * 1000));
            }
            if (lastMessage == null || message.date < lastMessage.date) {
              lastMessage=message;
            }
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("migrate step with id " + lastMessage.id + " date " + LocaleController.getInstance().formatterStats.format((long)lastMessage.date * 1000));
          }
          if (dialogsRes.dialogs.size() >= 100) {
            offsetId=lastMessage.id;
          }
 else {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("migrate stop due to not 100 dialogs");
            }
            for (int i=0; i < 2; i++) {
              UserConfig.getInstance(currentAccount).setDialogsLoadOffset(i,Integer.MAX_VALUE,UserConfig.getInstance(currentAccount).migrateOffsetDate,UserConfig.getInstance(currentAccount).migrateOffsetUserId,UserConfig.getInstance(currentAccount).migrateOffsetChatId,UserConfig.getInstance(currentAccount).migrateOffsetChannelId,UserConfig.getInstance(currentAccount).migrateOffsetAccess);
            }
            offsetId=-1;
          }
          StringBuilder dids=new StringBuilder(dialogsRes.dialogs.size() * 12);
          LongSparseArray<TLRPC.Dialog> dialogHashMap=new LongSparseArray<>();
          for (int a=0; a < dialogsRes.dialogs.size(); a++) {
            TLRPC.Dialog dialog=dialogsRes.dialogs.get(a);
            DialogObject.initDialog(dialog);
            if (dids.length() > 0) {
              dids.append(",");
            }
            dids.append(dialog.id);
            dialogHashMap.put(dialog.id,dialog);
          }
          SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT did, folder_id FROM dialogs WHERE did IN (%s)",dids.toString()));
          while (cursor.next()) {
            long did=cursor.longValue(0);
            int folder_id=cursor.intValue(1);
            TLRPC.Dialog dialog=dialogHashMap.get(did);
            if (dialog.folder_id != folder_id) {
              continue;
            }
            dialogHashMap.remove(did);
            if (dialog != null) {
              dialogsRes.dialogs.remove(dialog);
              for (int a=0; a < dialogsRes.messages.size(); a++) {
                TLRPC.Message message=dialogsRes.messages.get(a);
                if (MessageObject.getDialogId(message) != did) {
                  continue;
                }
                dialogsRes.messages.remove(a);
                a--;
                if (message.id == dialog.top_message) {
                  dialog.top_message=0;
                  break;
                }
              }
            }
          }
          cursor.dispose();
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("migrate found missing dialogs " + dialogsRes.dialogs.size());
          }
          cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT min(date) FROM dialogs WHERE date != 0 AND did >> 32 IN (0, -1)");
          if (cursor.next()) {
            int date=Math.max(1441062000,cursor.intValue(0));
            for (int a=0; a < dialogsRes.messages.size(); a++) {
              TLRPC.Message message=dialogsRes.messages.get(a);
              if (message.date < date) {
                if (offset != -1) {
                  for (int i=0; i < 2; i++) {
                    UserConfig.getInstance(currentAccount).setDialogsLoadOffset(i,UserConfig.getInstance(currentAccount).migrateOffsetId,UserConfig.getInstance(currentAccount).migrateOffsetDate,UserConfig.getInstance(currentAccount).migrateOffsetUserId,UserConfig.getInstance(currentAccount).migrateOffsetChatId,UserConfig.getInstance(currentAccount).migrateOffsetChannelId,UserConfig.getInstance(currentAccount).migrateOffsetAccess);
                  }
                  offsetId=-1;
                  if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("migrate stop due to reached loaded dialogs " + LocaleController.getInstance().formatterStats.format((long)date * 1000));
                  }
                }
                dialogsRes.messages.remove(a);
                a--;
                long did=MessageObject.getDialogId(message);
                TLRPC.Dialog dialog=dialogHashMap.get(did);
                dialogHashMap.remove(did);
                if (dialog != null) {
                  dialogsRes.dialogs.remove(dialog);
                }
              }
            }
            if (lastMessage != null && lastMessage.date < date && offset != -1) {
              for (int i=0; i < 2; i++) {
                UserConfig.getInstance(currentAccount).setDialogsLoadOffset(i,UserConfig.getInstance(currentAccount).migrateOffsetId,UserConfig.getInstance(currentAccount).migrateOffsetDate,UserConfig.getInstance(currentAccount).migrateOffsetUserId,UserConfig.getInstance(currentAccount).migrateOffsetChatId,UserConfig.getInstance(currentAccount).migrateOffsetChannelId,UserConfig.getInstance(currentAccount).migrateOffsetAccess);
              }
              offsetId=-1;
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("migrate stop due to reached loaded dialogs " + LocaleController.getInstance().formatterStats.format((long)date * 1000));
              }
            }
          }
          cursor.dispose();
          UserConfig.getInstance(currentAccount).migrateOffsetDate=lastMessage.date;
          if (lastMessage.to_id.channel_id != 0) {
            UserConfig.getInstance(currentAccount).migrateOffsetChannelId=lastMessage.to_id.channel_id;
            UserConfig.getInstance(currentAccount).migrateOffsetChatId=0;
            UserConfig.getInstance(currentAccount).migrateOffsetUserId=0;
            for (int a=0; a < dialogsRes.chats.size(); a++) {
              TLRPC.Chat chat=dialogsRes.chats.get(a);
              if (chat.id == UserConfig.getInstance(currentAccount).migrateOffsetChannelId) {
                UserConfig.getInstance(currentAccount).migrateOffsetAccess=chat.access_hash;
                break;
              }
            }
          }
 else           if (lastMessage.to_id.chat_id != 0) {
            UserConfig.getInstance(currentAccount).migrateOffsetChatId=lastMessage.to_id.chat_id;
            UserConfig.getInstance(currentAccount).migrateOffsetChannelId=0;
            UserConfig.getInstance(currentAccount).migrateOffsetUserId=0;
            for (int a=0; a < dialogsRes.chats.size(); a++) {
              TLRPC.Chat chat=dialogsRes.chats.get(a);
              if (chat.id == UserConfig.getInstance(currentAccount).migrateOffsetChatId) {
                UserConfig.getInstance(currentAccount).migrateOffsetAccess=chat.access_hash;
                break;
              }
            }
          }
 else           if (lastMessage.to_id.user_id != 0) {
            UserConfig.getInstance(currentAccount).migrateOffsetUserId=lastMessage.to_id.user_id;
            UserConfig.getInstance(currentAccount).migrateOffsetChatId=0;
            UserConfig.getInstance(currentAccount).migrateOffsetChannelId=0;
            for (int a=0; a < dialogsRes.users.size(); a++) {
              TLRPC.User user=dialogsRes.users.get(a);
              if (user.id == UserConfig.getInstance(currentAccount).migrateOffsetUserId) {
                UserConfig.getInstance(currentAccount).migrateOffsetAccess=user.access_hash;
                break;
              }
            }
          }
          processLoadedDialogs(dialogsRes,null,0,offsetId,0,0,false,true,false);
        }
 catch (        Exception e) {
          FileLog.e(e);
          AndroidUtilities.runOnUIThread(() -> migratingDialogs=false);
        }
      }
);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> migratingDialogs=false);
    }
  }
);
}
