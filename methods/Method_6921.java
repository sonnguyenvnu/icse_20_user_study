protected void getChannelDifference(final int channelId,final int newDialogType,final long taskId,TLRPC.InputChannel inputChannel){
  boolean gettingDifferenceChannel=gettingDifferenceChannels.get(channelId);
  if (gettingDifferenceChannel) {
    return;
  }
  int limit=100;
  int channelPts;
  if (newDialogType == 1) {
    channelPts=channelsPts.get(channelId);
    if (channelPts != 0) {
      return;
    }
    channelPts=1;
    limit=1;
  }
 else {
    channelPts=channelsPts.get(channelId);
    if (channelPts == 0) {
      channelPts=MessagesStorage.getInstance(currentAccount).getChannelPtsSync(channelId);
      if (channelPts != 0) {
        channelsPts.put(channelId,channelPts);
      }
      if (channelPts == 0 && (newDialogType == 2 || newDialogType == 3)) {
        return;
      }
    }
    if (channelPts == 0) {
      return;
    }
  }
  if (inputChannel == null) {
    TLRPC.Chat chat=getChat(channelId);
    if (chat == null) {
      chat=MessagesStorage.getInstance(currentAccount).getChatSync(channelId);
      if (chat != null) {
        putChat(chat,true);
      }
    }
    inputChannel=getInputChannel(chat);
  }
  if (inputChannel == null || inputChannel.access_hash == 0) {
    if (taskId != 0) {
      MessagesStorage.getInstance(currentAccount).removePendingTask(taskId);
    }
    return;
  }
  final long newTaskId;
  if (taskId == 0) {
    NativeByteBuffer data=null;
    try {
      data=new NativeByteBuffer(12 + inputChannel.getObjectSize());
      data.writeInt32(6);
      data.writeInt32(channelId);
      data.writeInt32(newDialogType);
      inputChannel.serializeToStream(data);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    newTaskId=MessagesStorage.getInstance(currentAccount).createPendingTask(data);
  }
 else {
    newTaskId=taskId;
  }
  gettingDifferenceChannels.put(channelId,true);
  TLRPC.TL_updates_getChannelDifference req=new TLRPC.TL_updates_getChannelDifference();
  req.channel=inputChannel;
  req.filter=new TLRPC.TL_channelMessagesFilterEmpty();
  req.pts=channelPts;
  req.limit=limit;
  req.force=newDialogType != 3;
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("start getChannelDifference with pts = " + channelPts + " channelId = " + channelId);
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      final TLRPC.updates_ChannelDifference res=(TLRPC.updates_ChannelDifference)response;
      final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
      for (int a=0; a < res.users.size(); a++) {
        TLRPC.User user=res.users.get(a);
        usersDict.put(user.id,user);
      }
      TLRPC.Chat channel=null;
      for (int a=0; a < res.chats.size(); a++) {
        TLRPC.Chat chat=res.chats.get(a);
        if (chat.id == channelId) {
          channel=chat;
          break;
        }
      }
      final TLRPC.Chat channelFinal=channel;
      final ArrayList<TLRPC.TL_updateMessageID> msgUpdates=new ArrayList<>();
      if (!res.other_updates.isEmpty()) {
        for (int a=0; a < res.other_updates.size(); a++) {
          TLRPC.Update upd=res.other_updates.get(a);
          if (upd instanceof TLRPC.TL_updateMessageID) {
            msgUpdates.add((TLRPC.TL_updateMessageID)upd);
            res.other_updates.remove(a);
            a--;
          }
        }
      }
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
      AndroidUtilities.runOnUIThread(() -> {
        putUsers(res.users,false);
        putChats(res.chats,false);
      }
);
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
        if (!msgUpdates.isEmpty()) {
          final SparseArray<long[]> corrected=new SparseArray<>();
          for (          TLRPC.TL_updateMessageID update : msgUpdates) {
            long[] ids=MessagesStorage.getInstance(currentAccount).updateMessageStateAndId(update.random_id,null,update.id,0,false,channelId);
            if (ids != null) {
              corrected.put(update.id,ids);
            }
          }
          if (corrected.size() != 0) {
            AndroidUtilities.runOnUIThread(() -> {
              for (int a=0; a < corrected.size(); a++) {
                int newId=corrected.keyAt(a);
                long[] ids=corrected.valueAt(a);
                int oldId=(int)ids[1];
                SendMessagesHelper.getInstance(currentAccount).processSentMessage(oldId);
                NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer,oldId,newId,null,ids[0],0L,-1);
              }
            }
);
          }
        }
        Utilities.stageQueue.postRunnable(() -> {
          if (res instanceof TLRPC.TL_updates_channelDifference || res instanceof TLRPC.TL_updates_channelDifferenceEmpty) {
            if (!res.new_messages.isEmpty()) {
              final LongSparseArray<ArrayList<MessageObject>> messages=new LongSparseArray<>();
              ImageLoader.saveMessagesThumbs(res.new_messages);
              final ArrayList<MessageObject> pushMessages=new ArrayList<>();
              long dialog_id=-channelId;
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
              for (int a=0; a < res.new_messages.size(); a++) {
                TLRPC.Message message=res.new_messages.get(a);
                message.unread=!(channelFinal != null && channelFinal.left || (message.out ? outboxValue : inboxValue) >= message.id || message.action instanceof TLRPC.TL_messageActionChannelCreate);
                if (channelFinal != null && channelFinal.megagroup) {
                  message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
                }
                MessageObject obj=new MessageObject(currentAccount,message,usersDict,createdDialogIds.contains(dialog_id));
                if (!obj.isOut() && obj.isUnread()) {
                  pushMessages.add(obj);
                }
                long uid=-channelId;
                ArrayList<MessageObject> arr=messages.get(uid);
                if (arr == null) {
                  arr=new ArrayList<>();
                  messages.put(uid,arr);
                }
                arr.add(obj);
              }
              AndroidUtilities.runOnUIThread(() -> {
                for (int a=0; a < messages.size(); a++) {
                  long key=messages.keyAt(a);
                  ArrayList<MessageObject> value=messages.valueAt(a);
                  updateInterfaceWithMessages(key,value);
                }
                NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
              }
);
              MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
                if (!pushMessages.isEmpty()) {
                  AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processNewMessages(pushMessages,true,false,null));
                }
                MessagesStorage.getInstance(currentAccount).putMessages(res.new_messages,true,false,false,DownloadController.getInstance(currentAccount).getAutodownloadMask());
              }
);
            }
            if (!res.other_updates.isEmpty()) {
              processUpdateArray(res.other_updates,res.users,res.chats,true);
            }
            processChannelsUpdatesQueue(channelId,1);
            MessagesStorage.getInstance(currentAccount).saveChannelPts(channelId,res.pts);
          }
 else           if (res instanceof TLRPC.TL_updates_channelDifferenceTooLong) {
            long dialog_id=-channelId;
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
            for (int a=0; a < res.messages.size(); a++) {
              TLRPC.Message message=res.messages.get(a);
              message.dialog_id=-channelId;
              message.unread=!(message.action instanceof TLRPC.TL_messageActionChannelCreate || channelFinal != null && channelFinal.left || (message.out ? outboxValue : inboxValue) >= message.id);
              if (channelFinal != null && channelFinal.megagroup) {
                message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
              }
            }
            MessagesStorage.getInstance(currentAccount).overwriteChannel(channelId,(TLRPC.TL_updates_channelDifferenceTooLong)res,newDialogType);
          }
          gettingDifferenceChannels.delete(channelId);
          channelsPts.put(channelId,res.pts);
          if ((res.flags & 2) != 0) {
            shortPollChannels.put(channelId,(int)(System.currentTimeMillis() / 1000) + res.timeout);
          }
          if (!res.isFinal) {
            getChannelDifference(channelId);
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("received channel difference with pts = " + res.pts + " channelId = " + channelId);
            FileLog.d("new_messages = " + res.new_messages.size() + " messages = " + res.messages.size() + " users = " + res.users.size() + " chats = " + res.chats.size() + " other updates = " + res.other_updates.size());
          }
          if (newTaskId != 0) {
            MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
          }
        }
);
      }
);
    }
 else     if (error != null) {
      AndroidUtilities.runOnUIThread(() -> checkChannelError(error.text,channelId));
      gettingDifferenceChannels.delete(channelId);
      if (newTaskId != 0) {
        MessagesStorage.getInstance(currentAccount).removePendingTask(newTaskId);
      }
    }
  }
);
}
