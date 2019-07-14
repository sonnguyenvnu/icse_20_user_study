public void processUpdates(final TLRPC.Updates updates,boolean fromQueue){
  ArrayList<Integer> needGetChannelsDiff=null;
  boolean needGetDiff=false;
  boolean needReceivedQueue=false;
  boolean updateStatus=false;
  if (updates instanceof TLRPC.TL_updateShort) {
    ArrayList<TLRPC.Update> arr=new ArrayList<>();
    arr.add(updates.update);
    processUpdateArray(arr,null,null,false);
  }
 else   if (updates instanceof TLRPC.TL_updateShortChatMessage || updates instanceof TLRPC.TL_updateShortMessage) {
    final int user_id=updates instanceof TLRPC.TL_updateShortChatMessage ? updates.from_id : updates.user_id;
    TLRPC.User user=getUser(user_id);
    TLRPC.User user2=null;
    TLRPC.User user3=null;
    TLRPC.Chat channel=null;
    if (user == null || user.min) {
      user=MessagesStorage.getInstance(currentAccount).getUserSync(user_id);
      if (user != null && user.min) {
        user=null;
      }
      putUser(user,true);
    }
    boolean needFwdUser=false;
    if (updates.fwd_from != null) {
      if (updates.fwd_from.from_id != 0) {
        user2=getUser(updates.fwd_from.from_id);
        if (user2 == null) {
          user2=MessagesStorage.getInstance(currentAccount).getUserSync(updates.fwd_from.from_id);
          putUser(user2,true);
        }
        needFwdUser=true;
      }
      if (updates.fwd_from.channel_id != 0) {
        channel=getChat(updates.fwd_from.channel_id);
        if (channel == null) {
          channel=MessagesStorage.getInstance(currentAccount).getChatSync(updates.fwd_from.channel_id);
          putChat(channel,true);
        }
        needFwdUser=true;
      }
    }
    boolean needBotUser=false;
    if (updates.via_bot_id != 0) {
      user3=getUser(updates.via_bot_id);
      if (user3 == null) {
        user3=MessagesStorage.getInstance(currentAccount).getUserSync(updates.via_bot_id);
        putUser(user3,true);
      }
      needBotUser=true;
    }
    boolean missingData;
    if (updates instanceof TLRPC.TL_updateShortMessage) {
      missingData=user == null || needFwdUser && user2 == null && channel == null || needBotUser && user3 == null;
    }
 else {
      TLRPC.Chat chat=getChat(updates.chat_id);
      if (chat == null) {
        chat=MessagesStorage.getInstance(currentAccount).getChatSync(updates.chat_id);
        putChat(chat,true);
      }
      missingData=chat == null || user == null || needFwdUser && user2 == null && channel == null || needBotUser && user3 == null;
    }
    if (!missingData && !updates.entities.isEmpty()) {
      for (int a=0; a < updates.entities.size(); a++) {
        TLRPC.MessageEntity entity=updates.entities.get(a);
        if (entity instanceof TLRPC.TL_messageEntityMentionName) {
          int uid=((TLRPC.TL_messageEntityMentionName)entity).user_id;
          TLRPC.User entityUser=getUser(uid);
          if (entityUser == null || entityUser.min) {
            entityUser=MessagesStorage.getInstance(currentAccount).getUserSync(uid);
            if (entityUser != null && entityUser.min) {
              entityUser=null;
            }
            if (entityUser == null) {
              missingData=true;
              break;
            }
            putUser(user,true);
          }
        }
      }
    }
    if (user != null && user.status != null && user.status.expires <= 0) {
      onlinePrivacy.put(user.id,ConnectionsManager.getInstance(currentAccount).getCurrentTime());
      updateStatus=true;
    }
    if (missingData) {
      needGetDiff=true;
    }
 else {
      if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() + updates.pts_count == updates.pts) {
        TLRPC.TL_message message=new TLRPC.TL_message();
        message.id=updates.id;
        int clientUserId=UserConfig.getInstance(currentAccount).getClientUserId();
        if (updates instanceof TLRPC.TL_updateShortMessage) {
          if (updates.out) {
            message.from_id=clientUserId;
          }
 else {
            message.from_id=user_id;
          }
          message.to_id=new TLRPC.TL_peerUser();
          message.to_id.user_id=user_id;
          message.dialog_id=user_id;
        }
 else {
          message.from_id=user_id;
          message.to_id=new TLRPC.TL_peerChat();
          message.to_id.chat_id=updates.chat_id;
          message.dialog_id=-updates.chat_id;
        }
        message.fwd_from=updates.fwd_from;
        message.silent=updates.silent;
        message.out=updates.out;
        message.mentioned=updates.mentioned;
        message.media_unread=updates.media_unread;
        message.entities=updates.entities;
        message.message=updates.message;
        message.date=updates.date;
        message.via_bot_id=updates.via_bot_id;
        message.flags=updates.flags | TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
        message.reply_to_msg_id=updates.reply_to_msg_id;
        message.media=new TLRPC.TL_messageMediaEmpty();
        ConcurrentHashMap<Long,Integer> read_max=message.out ? dialogs_read_outbox_max : dialogs_read_inbox_max;
        Integer value=read_max.get(message.dialog_id);
        if (value == null) {
          value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(message.out,message.dialog_id);
          read_max.put(message.dialog_id,value);
        }
        message.unread=value < message.id;
        if (message.dialog_id == clientUserId) {
          message.unread=false;
          message.media_unread=false;
          message.out=true;
        }
        MessagesStorage.getInstance(currentAccount).setLastPtsValue(updates.pts);
        final MessageObject obj=new MessageObject(currentAccount,message,createdDialogIds.contains(message.dialog_id));
        final ArrayList<MessageObject> objArr=new ArrayList<>();
        objArr.add(obj);
        ArrayList<TLRPC.Message> arr=new ArrayList<>();
        arr.add(message);
        if (updates instanceof TLRPC.TL_updateShortMessage) {
          final boolean printUpdate=!updates.out && updatePrintingUsersWithNewMessages(updates.user_id,objArr);
          if (printUpdate) {
            updatePrintingStrings();
          }
          AndroidUtilities.runOnUIThread(() -> {
            if (printUpdate) {
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_USER_PRINT);
            }
            updateInterfaceWithMessages(user_id,objArr);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
          }
);
        }
 else {
          final boolean printUpdate=updatePrintingUsersWithNewMessages(-updates.chat_id,objArr);
          if (printUpdate) {
            updatePrintingStrings();
          }
          AndroidUtilities.runOnUIThread(() -> {
            if (printUpdate) {
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_USER_PRINT);
            }
            updateInterfaceWithMessages(-updates.chat_id,objArr);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
          }
);
        }
        if (!obj.isOut()) {
          MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processNewMessages(objArr,true,false,null)));
        }
        MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
      }
 else       if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() != updates.pts) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("need get diff short message, pts: " + MessagesStorage.getInstance(currentAccount).getLastPtsValue() + " " + updates.pts + " count = " + updates.pts_count);
        }
        if (gettingDifference || updatesStartWaitTimePts == 0 || Math.abs(System.currentTimeMillis() - updatesStartWaitTimePts) <= 1500) {
          if (updatesStartWaitTimePts == 0) {
            updatesStartWaitTimePts=System.currentTimeMillis();
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("add to queue");
          }
          updatesQueuePts.add(updates);
        }
 else {
          needGetDiff=true;
        }
      }
    }
  }
 else   if (updates instanceof TLRPC.TL_updatesCombined || updates instanceof TLRPC.TL_updates) {
    SparseArray<TLRPC.Chat> minChannels=null;
    for (int a=0; a < updates.chats.size(); a++) {
      TLRPC.Chat chat=updates.chats.get(a);
      if (chat instanceof TLRPC.TL_channel) {
        if (chat.min) {
          TLRPC.Chat existChat=getChat(chat.id);
          if (existChat == null || existChat.min) {
            TLRPC.Chat cacheChat=MessagesStorage.getInstance(currentAccount).getChatSync(updates.chat_id);
            putChat(cacheChat,true);
            existChat=cacheChat;
          }
          if (existChat == null || existChat.min) {
            if (minChannels == null) {
              minChannels=new SparseArray<>();
            }
            minChannels.put(chat.id,chat);
          }
        }
      }
    }
    if (minChannels != null) {
      for (int a=0; a < updates.updates.size(); a++) {
        TLRPC.Update update=updates.updates.get(a);
        if (update instanceof TLRPC.TL_updateNewChannelMessage) {
          TLRPC.Message message=((TLRPC.TL_updateNewChannelMessage)update).message;
          int channelId=message.to_id.channel_id;
          if (minChannels.indexOfKey(channelId) >= 0) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("need get diff because of min channel " + channelId);
            }
            needGetDiff=true;
            break;
          }
        }
      }
    }
    if (!needGetDiff) {
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(updates.users,updates.chats,true,true);
      Collections.sort(updates.updates,updatesComparator);
      for (int a=0; a < updates.updates.size(); a++) {
        TLRPC.Update update=updates.updates.get(a);
        if (getUpdateType(update) == 0) {
          TLRPC.TL_updates updatesNew=new TLRPC.TL_updates();
          updatesNew.updates.add(update);
          updatesNew.pts=getUpdatePts(update);
          updatesNew.pts_count=getUpdatePtsCount(update);
          for (int b=a + 1; b < updates.updates.size(); b++) {
            TLRPC.Update update2=updates.updates.get(b);
            int pts2=getUpdatePts(update2);
            int count2=getUpdatePtsCount(update2);
            if (getUpdateType(update2) == 0 && updatesNew.pts + count2 == pts2) {
              updatesNew.updates.add(update2);
              updatesNew.pts=pts2;
              updatesNew.pts_count+=count2;
              updates.updates.remove(b);
              b--;
            }
 else {
              break;
            }
          }
          if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() + updatesNew.pts_count == updatesNew.pts) {
            if (!processUpdateArray(updatesNew.updates,updates.users,updates.chats,false)) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("need get diff inner TL_updates, pts: " + MessagesStorage.getInstance(currentAccount).getLastPtsValue() + " " + updates.seq);
              }
              needGetDiff=true;
            }
 else {
              MessagesStorage.getInstance(currentAccount).setLastPtsValue(updatesNew.pts);
            }
          }
 else           if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() != updatesNew.pts) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d(update + " need get diff, pts: " + MessagesStorage.getInstance(currentAccount).getLastPtsValue() + " " + updatesNew.pts + " count = " + updatesNew.pts_count);
            }
            if (gettingDifference || updatesStartWaitTimePts == 0 || updatesStartWaitTimePts != 0 && Math.abs(System.currentTimeMillis() - updatesStartWaitTimePts) <= 1500) {
              if (updatesStartWaitTimePts == 0) {
                updatesStartWaitTimePts=System.currentTimeMillis();
              }
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("add to queue");
              }
              updatesQueuePts.add(updatesNew);
            }
 else {
              needGetDiff=true;
            }
          }
        }
 else         if (getUpdateType(update) == 1) {
          TLRPC.TL_updates updatesNew=new TLRPC.TL_updates();
          updatesNew.updates.add(update);
          updatesNew.pts=getUpdateQts(update);
          for (int b=a + 1; b < updates.updates.size(); b++) {
            TLRPC.Update update2=updates.updates.get(b);
            int qts2=getUpdateQts(update2);
            if (getUpdateType(update2) == 1 && updatesNew.pts + 1 == qts2) {
              updatesNew.updates.add(update2);
              updatesNew.pts=qts2;
              updates.updates.remove(b);
              b--;
            }
 else {
              break;
            }
          }
          if (MessagesStorage.getInstance(currentAccount).getLastQtsValue() == 0 || MessagesStorage.getInstance(currentAccount).getLastQtsValue() + updatesNew.updates.size() == updatesNew.pts) {
            processUpdateArray(updatesNew.updates,updates.users,updates.chats,false);
            MessagesStorage.getInstance(currentAccount).setLastQtsValue(updatesNew.pts);
            needReceivedQueue=true;
          }
 else           if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() != updatesNew.pts) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d(update + " need get diff, qts: " + MessagesStorage.getInstance(currentAccount).getLastQtsValue() + " " + updatesNew.pts);
            }
            if (gettingDifference || updatesStartWaitTimeQts == 0 || updatesStartWaitTimeQts != 0 && Math.abs(System.currentTimeMillis() - updatesStartWaitTimeQts) <= 1500) {
              if (updatesStartWaitTimeQts == 0) {
                updatesStartWaitTimeQts=System.currentTimeMillis();
              }
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("add to queue");
              }
              updatesQueueQts.add(updatesNew);
            }
 else {
              needGetDiff=true;
            }
          }
        }
 else         if (getUpdateType(update) == 2) {
          int channelId=getUpdateChannelId(update);
          boolean skipUpdate=false;
          int channelPts=channelsPts.get(channelId);
          if (channelPts == 0) {
            channelPts=MessagesStorage.getInstance(currentAccount).getChannelPtsSync(channelId);
            if (channelPts == 0) {
              for (int c=0; c < updates.chats.size(); c++) {
                TLRPC.Chat chat=updates.chats.get(c);
                if (chat.id == channelId) {
                  loadUnknownChannel(chat,0);
                  skipUpdate=true;
                  break;
                }
              }
            }
 else {
              channelsPts.put(channelId,channelPts);
            }
          }
          TLRPC.TL_updates updatesNew=new TLRPC.TL_updates();
          updatesNew.updates.add(update);
          updatesNew.pts=getUpdatePts(update);
          updatesNew.pts_count=getUpdatePtsCount(update);
          for (int b=a + 1; b < updates.updates.size(); b++) {
            TLRPC.Update update2=updates.updates.get(b);
            int pts2=getUpdatePts(update2);
            int count2=getUpdatePtsCount(update2);
            if (getUpdateType(update2) == 2 && channelId == getUpdateChannelId(update2) && updatesNew.pts + count2 == pts2) {
              updatesNew.updates.add(update2);
              updatesNew.pts=pts2;
              updatesNew.pts_count+=count2;
              updates.updates.remove(b);
              b--;
            }
 else {
              break;
            }
          }
          if (!skipUpdate) {
            if (channelPts + updatesNew.pts_count == updatesNew.pts) {
              if (!processUpdateArray(updatesNew.updates,updates.users,updates.chats,false)) {
                if (BuildVars.LOGS_ENABLED) {
                  FileLog.d("need get channel diff inner TL_updates, channel_id = " + channelId);
                }
                if (needGetChannelsDiff == null) {
                  needGetChannelsDiff=new ArrayList<>();
                }
 else                 if (!needGetChannelsDiff.contains(channelId)) {
                  needGetChannelsDiff.add(channelId);
                }
              }
 else {
                channelsPts.put(channelId,updatesNew.pts);
                MessagesStorage.getInstance(currentAccount).saveChannelPts(channelId,updatesNew.pts);
              }
            }
 else             if (channelPts != updatesNew.pts) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d(update + " need get channel diff, pts: " + channelPts + " " + updatesNew.pts + " count = " + updatesNew.pts_count + " channelId = " + channelId);
              }
              long updatesStartWaitTime=updatesStartWaitTimeChannels.get(channelId);
              boolean gettingDifferenceChannel=gettingDifferenceChannels.get(channelId);
              if (gettingDifferenceChannel || updatesStartWaitTime == 0 || Math.abs(System.currentTimeMillis() - updatesStartWaitTime) <= 1500) {
                if (updatesStartWaitTime == 0) {
                  updatesStartWaitTimeChannels.put(channelId,System.currentTimeMillis());
                }
                if (BuildVars.LOGS_ENABLED) {
                  FileLog.d("add to queue");
                }
                ArrayList<TLRPC.Updates> arrayList=updatesQueueChannels.get(channelId);
                if (arrayList == null) {
                  arrayList=new ArrayList<>();
                  updatesQueueChannels.put(channelId,arrayList);
                }
                arrayList.add(updatesNew);
              }
 else {
                if (needGetChannelsDiff == null) {
                  needGetChannelsDiff=new ArrayList<>();
                }
 else                 if (!needGetChannelsDiff.contains(channelId)) {
                  needGetChannelsDiff.add(channelId);
                }
              }
            }
          }
 else {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("need load unknown channel = " + channelId);
            }
          }
        }
 else {
          break;
        }
        updates.updates.remove(a);
        a--;
      }
      boolean processUpdate;
      if (updates instanceof TLRPC.TL_updatesCombined) {
        processUpdate=MessagesStorage.getInstance(currentAccount).getLastSeqValue() + 1 == updates.seq_start || MessagesStorage.getInstance(currentAccount).getLastSeqValue() == updates.seq_start;
      }
 else {
        processUpdate=MessagesStorage.getInstance(currentAccount).getLastSeqValue() + 1 == updates.seq || updates.seq == 0 || updates.seq == MessagesStorage.getInstance(currentAccount).getLastSeqValue();
      }
      if (processUpdate) {
        processUpdateArray(updates.updates,updates.users,updates.chats,false);
        if (updates.seq != 0) {
          if (updates.date != 0) {
            MessagesStorage.getInstance(currentAccount).setLastDateValue(updates.date);
          }
          MessagesStorage.getInstance(currentAccount).setLastSeqValue(updates.seq);
        }
      }
 else {
        if (BuildVars.LOGS_ENABLED) {
          if (updates instanceof TLRPC.TL_updatesCombined) {
            FileLog.d("need get diff TL_updatesCombined, seq: " + MessagesStorage.getInstance(currentAccount).getLastSeqValue() + " " + updates.seq_start);
          }
 else {
            FileLog.d("need get diff TL_updates, seq: " + MessagesStorage.getInstance(currentAccount).getLastSeqValue() + " " + updates.seq);
          }
        }
        if (gettingDifference || updatesStartWaitTimeSeq == 0 || Math.abs(System.currentTimeMillis() - updatesStartWaitTimeSeq) <= 1500) {
          if (updatesStartWaitTimeSeq == 0) {
            updatesStartWaitTimeSeq=System.currentTimeMillis();
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("add TL_updates/Combined to queue");
          }
          updatesQueueSeq.add(updates);
        }
 else {
          needGetDiff=true;
        }
      }
    }
  }
 else   if (updates instanceof TLRPC.TL_updatesTooLong) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("need get diff TL_updatesTooLong");
    }
    needGetDiff=true;
  }
 else   if (updates instanceof UserActionUpdatesSeq) {
    MessagesStorage.getInstance(currentAccount).setLastSeqValue(updates.seq);
  }
 else   if (updates instanceof UserActionUpdatesPts) {
    if (updates.chat_id != 0) {
      channelsPts.put(updates.chat_id,updates.pts);
      MessagesStorage.getInstance(currentAccount).saveChannelPts(updates.chat_id,updates.pts);
    }
 else {
      MessagesStorage.getInstance(currentAccount).setLastPtsValue(updates.pts);
    }
  }
  SecretChatHelper.getInstance(currentAccount).processPendingEncMessages();
  if (!fromQueue) {
    for (int a=0; a < updatesQueueChannels.size(); a++) {
      int key=updatesQueueChannels.keyAt(a);
      if (needGetChannelsDiff != null && needGetChannelsDiff.contains(key)) {
        getChannelDifference(key);
      }
 else {
        processChannelsUpdatesQueue(key,0);
      }
    }
    if (needGetDiff) {
      getDifference();
    }
 else {
      for (int a=0; a < 3; a++) {
        processUpdatesQueue(a,0);
      }
    }
  }
  if (needReceivedQueue) {
    TLRPC.TL_messages_receivedQueue req=new TLRPC.TL_messages_receivedQueue();
    req.max_qts=MessagesStorage.getInstance(currentAccount).getLastQtsValue();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
  if (updateStatus) {
    AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_STATUS));
  }
  MessagesStorage.getInstance(currentAccount).saveDiffParams(MessagesStorage.getInstance(currentAccount).getLastSeqValue(),MessagesStorage.getInstance(currentAccount).getLastPtsValue(),MessagesStorage.getInstance(currentAccount).getLastDateValue(),MessagesStorage.getInstance(currentAccount).getLastQtsValue());
}
