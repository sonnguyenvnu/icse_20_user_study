public void getDifference(int pts,final int date,final int qts,boolean slice){
  registerForPush(SharedConfig.pushString);
  if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() == 0) {
    loadCurrentState();
    return;
  }
  if (!slice && gettingDifference) {
    return;
  }
  gettingDifference=true;
  TLRPC.TL_updates_getDifference req=new TLRPC.TL_updates_getDifference();
  req.pts=pts;
  req.date=date;
  req.qts=qts;
  if (getDifferenceFirstSync) {
    req.flags|=1;
    if (ApplicationLoader.isConnectedOrConnectingToWiFi()) {
      req.pts_total_limit=5000;
    }
 else {
      req.pts_total_limit=1000;
    }
    getDifferenceFirstSync=false;
  }
  if (req.date == 0) {
    req.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  }
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("start getDifference with date = " + date + " pts = " + pts + " qts = " + qts);
  }
  ConnectionsManager.getInstance(currentAccount).setIsUpdating(true);
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      final TLRPC.updates_Difference res=(TLRPC.updates_Difference)response;
      if (res instanceof TLRPC.TL_updates_differenceTooLong) {
        AndroidUtilities.runOnUIThread(() -> {
          loadedFullUsers.clear();
          loadedFullChats.clear();
          resetDialogs(true,MessagesStorage.getInstance(currentAccount).getLastSeqValue(),res.pts,date,qts);
        }
);
      }
 else {
        if (res instanceof TLRPC.TL_updates_differenceSlice) {
          getDifference(res.intermediate_state.pts,res.intermediate_state.date,res.intermediate_state.qts,true);
        }
        final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
        final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
        for (int a=0; a < res.users.size(); a++) {
          TLRPC.User user=res.users.get(a);
          usersDict.put(user.id,user);
        }
        for (int a=0; a < res.chats.size(); a++) {
          TLRPC.Chat chat=res.chats.get(a);
          chatsDict.put(chat.id,chat);
        }
        final ArrayList<TLRPC.TL_updateMessageID> msgUpdates=new ArrayList<>();
        if (!res.other_updates.isEmpty()) {
          for (int a=0; a < res.other_updates.size(); a++) {
            TLRPC.Update upd=res.other_updates.get(a);
            if (upd instanceof TLRPC.TL_updateMessageID) {
              msgUpdates.add((TLRPC.TL_updateMessageID)upd);
              res.other_updates.remove(a);
              a--;
            }
 else             if (getUpdateType(upd) == 2) {
              int channelId=getUpdateChannelId(upd);
              int channelPts=channelsPts.get(channelId);
              if (channelPts == 0) {
                channelPts=MessagesStorage.getInstance(currentAccount).getChannelPtsSync(channelId);
                if (channelPts != 0) {
                  channelsPts.put(channelId,channelPts);
                }
              }
              if (channelPts != 0 && getUpdatePts(upd) <= channelPts) {
                res.other_updates.remove(a);
                a--;
              }
            }
          }
        }
        AndroidUtilities.runOnUIThread(() -> {
          loadedFullUsers.clear();
          loadedFullChats.clear();
          putUsers(res.users,false);
          putChats(res.chats,false);
        }
);
        MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
          MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,false);
          if (!msgUpdates.isEmpty()) {
            final SparseArray<long[]> corrected=new SparseArray<>();
            for (int a=0; a < msgUpdates.size(); a++) {
              TLRPC.TL_updateMessageID update=msgUpdates.get(a);
              long[] ids=MessagesStorage.getInstance(currentAccount).updateMessageStateAndId(update.random_id,null,update.id,0,false,0);
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
            if (!res.new_messages.isEmpty() || !res.new_encrypted_messages.isEmpty()) {
              final LongSparseArray<ArrayList<MessageObject>> messages=new LongSparseArray<>();
              for (int b=0; b < res.new_encrypted_messages.size(); b++) {
                TLRPC.EncryptedMessage encryptedMessage=res.new_encrypted_messages.get(b);
                ArrayList<TLRPC.Message> decryptedMessages=SecretChatHelper.getInstance(currentAccount).decryptMessage(encryptedMessage);
                if (decryptedMessages != null && !decryptedMessages.isEmpty()) {
                  res.new_messages.addAll(decryptedMessages);
                }
              }
              ImageLoader.saveMessagesThumbs(res.new_messages);
              final ArrayList<MessageObject> pushMessages=new ArrayList<>();
              int clientUserId=UserConfig.getInstance(currentAccount).getClientUserId();
              for (int a=0; a < res.new_messages.size(); a++) {
                TLRPC.Message message=res.new_messages.get(a);
                if (message.dialog_id == 0) {
                  if (message.to_id.chat_id != 0) {
                    message.dialog_id=-message.to_id.chat_id;
                  }
 else {
                    if (message.to_id.user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
                      message.to_id.user_id=message.from_id;
                    }
                    message.dialog_id=message.to_id.user_id;
                  }
                }
                if ((int)message.dialog_id != 0) {
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
                if (message.dialog_id == clientUserId) {
                  message.unread=false;
                  message.media_unread=false;
                  message.out=true;
                }
                MessageObject obj=new MessageObject(currentAccount,message,usersDict,chatsDict,createdDialogIds.contains(message.dialog_id));
                if (!obj.isOut() && obj.isUnread()) {
                  pushMessages.add(obj);
                }
                ArrayList<MessageObject> arr=messages.get(message.dialog_id);
                if (arr == null) {
                  arr=new ArrayList<>();
                  messages.put(message.dialog_id,arr);
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
                  AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processNewMessages(pushMessages,!(res instanceof TLRPC.TL_updates_differenceSlice),false,null));
                }
                MessagesStorage.getInstance(currentAccount).putMessages(res.new_messages,true,false,false,DownloadController.getInstance(currentAccount).getAutodownloadMask());
              }
);
              SecretChatHelper.getInstance(currentAccount).processPendingEncMessages();
            }
            if (!res.other_updates.isEmpty()) {
              processUpdateArray(res.other_updates,res.users,res.chats,true);
            }
            if (res instanceof TLRPC.TL_updates_difference) {
              gettingDifference=false;
              MessagesStorage.getInstance(currentAccount).setLastSeqValue(res.state.seq);
              MessagesStorage.getInstance(currentAccount).setLastDateValue(res.state.date);
              MessagesStorage.getInstance(currentAccount).setLastPtsValue(res.state.pts);
              MessagesStorage.getInstance(currentAccount).setLastQtsValue(res.state.qts);
              ConnectionsManager.getInstance(currentAccount).setIsUpdating(false);
              for (int a=0; a < 3; a++) {
                processUpdatesQueue(a,1);
              }
            }
 else             if (res instanceof TLRPC.TL_updates_differenceSlice) {
              MessagesStorage.getInstance(currentAccount).setLastDateValue(res.intermediate_state.date);
              MessagesStorage.getInstance(currentAccount).setLastPtsValue(res.intermediate_state.pts);
              MessagesStorage.getInstance(currentAccount).setLastQtsValue(res.intermediate_state.qts);
            }
 else             if (res instanceof TLRPC.TL_updates_differenceEmpty) {
              gettingDifference=false;
              MessagesStorage.getInstance(currentAccount).setLastSeqValue(res.seq);
              MessagesStorage.getInstance(currentAccount).setLastDateValue(res.date);
              ConnectionsManager.getInstance(currentAccount).setIsUpdating(false);
              for (int a=0; a < 3; a++) {
                processUpdatesQueue(a,1);
              }
            }
            MessagesStorage.getInstance(currentAccount).saveDiffParams(MessagesStorage.getInstance(currentAccount).getLastSeqValue(),MessagesStorage.getInstance(currentAccount).getLastPtsValue(),MessagesStorage.getInstance(currentAccount).getLastDateValue(),MessagesStorage.getInstance(currentAccount).getLastQtsValue());
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("received difference with date = " + MessagesStorage.getInstance(currentAccount).getLastDateValue() + " pts = " + MessagesStorage.getInstance(currentAccount).getLastPtsValue() + " seq = " + MessagesStorage.getInstance(currentAccount).getLastSeqValue() + " messages = " + res.new_messages.size() + " users = " + res.users.size() + " chats = " + res.chats.size() + " other updates = " + res.other_updates.size());
            }
          }
);
        }
);
      }
    }
 else {
      gettingDifference=false;
      ConnectionsManager.getInstance(currentAccount).setIsUpdating(false);
    }
  }
);
}
