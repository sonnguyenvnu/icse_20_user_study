public void loadUnreadMessages(){
  storageQueue.postRunnable(() -> {
    try {
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      ArrayList<Integer> chatsToLoad=new ArrayList<>();
      ArrayList<Integer> encryptedChatIds=new ArrayList<>();
      final LongSparseArray<Integer> pushDialogs=new LongSparseArray<>();
      SQLiteCursor cursor=database.queryFinalized("SELECT d.did, d.unread_count, s.flags FROM dialogs as d LEFT JOIN dialog_settings as s ON d.did = s.did WHERE d.unread_count != 0");
      StringBuilder ids=new StringBuilder();
      int currentTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
      while (cursor.next()) {
        long flags=cursor.longValue(2);
        boolean muted=(flags & 1) != 0;
        int mutedUntil=(int)(flags >> 32);
        if (cursor.isNull(2) || !muted || mutedUntil != 0 && mutedUntil < currentTime) {
          long did=cursor.longValue(0);
          if (DialogObject.isFolderDialogId(did)) {
            continue;
          }
          int count=cursor.intValue(1);
          pushDialogs.put(did,count);
          if (ids.length() != 0) {
            ids.append(",");
          }
          ids.append(did);
          int lower_id=(int)did;
          int high_id=(int)(did >> 32);
          if (lower_id != 0) {
            if (lower_id < 0) {
              if (!chatsToLoad.contains(-lower_id)) {
                chatsToLoad.add(-lower_id);
              }
            }
 else {
              if (!usersToLoad.contains(lower_id)) {
                usersToLoad.add(lower_id);
              }
            }
          }
 else {
            if (!encryptedChatIds.contains(high_id)) {
              encryptedChatIds.add(high_id);
            }
          }
        }
      }
      cursor.dispose();
      ArrayList<Long> replyMessages=new ArrayList<>();
      SparseArray<ArrayList<TLRPC.Message>> replyMessageOwners=new SparseArray<>();
      final ArrayList<TLRPC.Message> messages=new ArrayList<>();
      final ArrayList<MessageObject> pushMessages=new ArrayList<>();
      final ArrayList<TLRPC.User> users=new ArrayList<>();
      final ArrayList<TLRPC.Chat> chats=new ArrayList<>();
      final ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
      int maxDate=0;
      if (ids.length() > 0) {
        cursor=database.queryFinalized("SELECT read_state, data, send_state, mid, date, uid, replydata FROM messages WHERE uid IN (" + ids.toString() + ") AND out = 0 AND read_state IN(0,2) ORDER BY date DESC LIMIT 50");
        while (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(1);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
            data.reuse();
            MessageObject.setUnreadFlags(message,cursor.intValue(0));
            message.id=cursor.intValue(3);
            message.date=cursor.intValue(4);
            message.dialog_id=cursor.longValue(5);
            messages.add(message);
            maxDate=Math.max(maxDate,message.date);
            int lower_id=(int)message.dialog_id;
            addUsersAndChatsFromMessage(message,usersToLoad,chatsToLoad);
            message.send_state=cursor.intValue(2);
            if (message.to_id.channel_id == 0 && !MessageObject.isUnread(message) && lower_id != 0 || message.id > 0) {
              message.send_state=0;
            }
            if (lower_id == 0 && !cursor.isNull(5)) {
              message.random_id=cursor.longValue(5);
            }
            try {
              if (message.reply_to_msg_id != 0 && (message.action instanceof TLRPC.TL_messageActionPinMessage || message.action instanceof TLRPC.TL_messageActionPaymentSent || message.action instanceof TLRPC.TL_messageActionGameScore)) {
                if (!cursor.isNull(6)) {
                  data=cursor.byteBufferValue(6);
                  if (data != null) {
                    message.replyMessage=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
                    message.replyMessage.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
                    data.reuse();
                    if (message.replyMessage != null) {
                      if (MessageObject.isMegagroup(message)) {
                        message.replyMessage.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
                      }
                      addUsersAndChatsFromMessage(message.replyMessage,usersToLoad,chatsToLoad);
                    }
                  }
                }
                if (message.replyMessage == null) {
                  long messageId=message.reply_to_msg_id;
                  if (message.to_id.channel_id != 0) {
                    messageId|=((long)message.to_id.channel_id) << 32;
                  }
                  if (!replyMessages.contains(messageId)) {
                    replyMessages.add(messageId);
                  }
                  ArrayList<TLRPC.Message> arrayList=replyMessageOwners.get(message.reply_to_msg_id);
                  if (arrayList == null) {
                    arrayList=new ArrayList<>();
                    replyMessageOwners.put(message.reply_to_msg_id,arrayList);
                  }
                  arrayList.add(message);
                }
              }
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
        cursor.dispose();
        database.executeFast("DELETE FROM unread_push_messages WHERE date <= " + maxDate).stepThis().dispose();
        cursor=database.queryFinalized("SELECT data, mid, date, uid, random, fm, name, uname, flags FROM unread_push_messages WHERE 1 ORDER BY date DESC LIMIT 50");
        while (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            data.reuse();
            message.id=cursor.intValue(1);
            message.date=cursor.intValue(2);
            message.dialog_id=cursor.longValue(3);
            message.random_id=cursor.longValue(4);
            String messageText=cursor.isNull(5) ? null : cursor.stringValue(5);
            String name=cursor.isNull(6) ? null : cursor.stringValue(6);
            String userName=cursor.isNull(7) ? null : cursor.stringValue(7);
            int flags=cursor.intValue(8);
            pushMessages.add(new MessageObject(currentAccount,message,messageText,name,userName,(flags & 1) != 0,(flags & 2) != 0,false));
            addUsersAndChatsFromMessage(message,usersToLoad,chatsToLoad);
          }
        }
        cursor.dispose();
        if (!replyMessages.isEmpty()) {
          cursor=database.queryFinalized(String.format(Locale.US,"SELECT data, mid, date, uid FROM messages WHERE mid IN(%s)",TextUtils.join(",",replyMessages)));
          while (cursor.next()) {
            NativeByteBuffer data=cursor.byteBufferValue(0);
            if (data != null) {
              TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
              message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
              data.reuse();
              message.id=cursor.intValue(1);
              message.date=cursor.intValue(2);
              message.dialog_id=cursor.longValue(3);
              addUsersAndChatsFromMessage(message,usersToLoad,chatsToLoad);
              ArrayList<TLRPC.Message> arrayList=replyMessageOwners.get(message.id);
              if (arrayList != null) {
                for (int a=0; a < arrayList.size(); a++) {
                  TLRPC.Message m=arrayList.get(a);
                  m.replyMessage=message;
                  if (MessageObject.isMegagroup(m)) {
                    m.replyMessage.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
                  }
                }
              }
            }
          }
          cursor.dispose();
        }
        if (!encryptedChatIds.isEmpty()) {
          getEncryptedChatsInternal(TextUtils.join(",",encryptedChatIds),encryptedChats,usersToLoad);
        }
        if (!usersToLoad.isEmpty()) {
          getUsersInternal(TextUtils.join(",",usersToLoad),users);
        }
        if (!chatsToLoad.isEmpty()) {
          getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
          for (int a=0; a < chats.size(); a++) {
            TLRPC.Chat chat=chats.get(a);
            if (chat != null && (chat.left || chat.migrated_to != null)) {
              long did=-chat.id;
              database.executeFast("UPDATE dialogs SET unread_count = 0 WHERE did = " + did).stepThis().dispose();
              database.executeFast(String.format(Locale.US,"UPDATE messages SET read_state = 3 WHERE uid = %d AND mid > 0 AND read_state IN(0,2) AND out = 0",did)).stepThis().dispose();
              chats.remove(a);
              a--;
              pushDialogs.remove((long)-chat.id);
              for (int b=0; b < messages.size(); b++) {
                TLRPC.Message message=messages.get(b);
                if (message.dialog_id == -chat.id) {
                  messages.remove(b);
                  b--;
                }
              }
            }
          }
        }
      }
      Collections.reverse(messages);
      AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processLoadedUnreadMessages(pushDialogs,messages,pushMessages,users,chats,encryptedChats));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
