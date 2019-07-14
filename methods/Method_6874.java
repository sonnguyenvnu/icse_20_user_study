public void processLoadedMessages(final TLRPC.messages_Messages messagesRes,final long dialog_id,final int count,final int max_id,final int offset_date,final boolean isCache,final int classGuid,final int first_unread,final int last_message_id,final int unread_count,final int last_date,final int load_type,final boolean isChannel,final boolean isEnd,final int loadIndex,final boolean queryFromServer,final int mentionsCount){
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("processLoadedMessages size " + messagesRes.messages.size() + " in chat " + dialog_id + " count " + count + " max_id " + max_id + " cache " + isCache + " guid " + classGuid + " load_type " + load_type + " last_message_id " + last_message_id + " isChannel " + isChannel + " index " + loadIndex + " firstUnread " + first_unread + " unread_count " + unread_count + " last_date " + last_date + " queryFromServer " + queryFromServer);
  }
  Utilities.stageQueue.postRunnable(() -> {
    boolean createDialog=false;
    boolean isMegagroup=false;
    if (messagesRes instanceof TLRPC.TL_messages_channelMessages) {
      int channelId=-(int)dialog_id;
      int channelPts=channelsPts.get(channelId);
      if (channelPts == 0) {
        channelPts=MessagesStorage.getInstance(currentAccount).getChannelPtsSync(channelId);
        if (channelPts == 0) {
          channelsPts.put(channelId,messagesRes.pts);
          createDialog=true;
          if (needShortPollChannels.indexOfKey(channelId) >= 0 && shortPollChannels.indexOfKey(channelId) < 0) {
            getChannelDifference(channelId,2,0,null);
          }
 else {
            getChannelDifference(channelId);
          }
        }
      }
      for (int a=0; a < messagesRes.chats.size(); a++) {
        TLRPC.Chat chat=messagesRes.chats.get(a);
        if (chat.id == channelId) {
          isMegagroup=chat.megagroup;
          break;
        }
      }
    }
    int lower_id=(int)dialog_id;
    int high_id=(int)(dialog_id >> 32);
    if (!isCache) {
      ImageLoader.saveMessagesThumbs(messagesRes.messages);
    }
    if (high_id != 1 && lower_id != 0 && isCache && messagesRes.messages.size() == 0) {
      AndroidUtilities.runOnUIThread(() -> loadMessages(dialog_id,count,load_type == 2 && queryFromServer ? first_unread : max_id,offset_date,false,0,classGuid,load_type,last_message_id,isChannel,loadIndex,first_unread,unread_count,last_date,queryFromServer,mentionsCount));
      return;
    }
    final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
    final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
    for (int a=0; a < messagesRes.users.size(); a++) {
      TLRPC.User u=messagesRes.users.get(a);
      usersDict.put(u.id,u);
    }
    for (int a=0; a < messagesRes.chats.size(); a++) {
      TLRPC.Chat c=messagesRes.chats.get(a);
      chatsDict.put(c.id,c);
    }
    int size=messagesRes.messages.size();
    if (!isCache) {
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
      for (int a=0; a < size; a++) {
        TLRPC.Message message=messagesRes.messages.get(a);
        if (isMegagroup) {
          message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
        }
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
          message.unread=(message.out ? outboxValue : inboxValue) < message.id;
        }
      }
      MessagesStorage.getInstance(currentAccount).putMessages(messagesRes,dialog_id,load_type,max_id,createDialog);
    }
    final ArrayList<MessageObject> objects=new ArrayList<>();
    final ArrayList<Integer> messagesToReload=new ArrayList<>();
    final HashMap<String,ArrayList<MessageObject>> webpagesToReload=new HashMap<>();
    TLRPC.InputChannel inputChannel=null;
    for (int a=0; a < size; a++) {
      TLRPC.Message message=messagesRes.messages.get(a);
      message.dialog_id=dialog_id;
      MessageObject messageObject=new MessageObject(currentAccount,message,usersDict,chatsDict,true);
      objects.add(messageObject);
      if (isCache) {
        if (message.legacy && message.layer < TLRPC.LAYER) {
          messagesToReload.add(message.id);
        }
 else         if (message.media instanceof TLRPC.TL_messageMediaUnsupported) {
          if (message.media.bytes != null && (message.media.bytes.length == 0 || message.media.bytes.length == 1 && message.media.bytes[0] < TLRPC.LAYER)) {
            messagesToReload.add(message.id);
          }
        }
        if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
          if (message.media.webpage instanceof TLRPC.TL_webPagePending && message.media.webpage.date <= ConnectionsManager.getInstance(currentAccount).getCurrentTime()) {
            messagesToReload.add(message.id);
          }
 else           if (message.media.webpage instanceof TLRPC.TL_webPageUrlPending) {
            ArrayList<MessageObject> arrayList=webpagesToReload.get(message.media.webpage.url);
            if (arrayList == null) {
              arrayList=new ArrayList<>();
              webpagesToReload.put(message.media.webpage.url,arrayList);
            }
            arrayList.add(messageObject);
          }
        }
      }
    }
    AndroidUtilities.runOnUIThread(() -> {
      putUsers(messagesRes.users,isCache);
      putChats(messagesRes.chats,isCache);
      int first_unread_final=Integer.MAX_VALUE;
      if (queryFromServer && load_type == 2) {
        for (int a=0; a < messagesRes.messages.size(); a++) {
          TLRPC.Message message=messagesRes.messages.get(a);
          if (!message.out && message.id > first_unread && message.id < first_unread_final) {
            first_unread_final=message.id;
          }
        }
      }
      if (first_unread_final == Integer.MAX_VALUE) {
        first_unread_final=first_unread;
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesDidLoad,dialog_id,count,objects,isCache,first_unread_final,last_message_id,unread_count,last_date,load_type,isEnd,classGuid,loadIndex,max_id,mentionsCount);
      if (!messagesToReload.isEmpty()) {
        reloadMessages(messagesToReload,dialog_id);
      }
      if (!webpagesToReload.isEmpty()) {
        reloadWebPages(dialog_id,webpagesToReload);
      }
    }
);
  }
);
}
