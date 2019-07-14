public void putMessages(final TLRPC.messages_Messages messages,final long dialog_id,final int load_type,final int max_id,final boolean createDialog){
  storageQueue.postRunnable(() -> {
    try {
      int mentionCountUpdate=Integer.MAX_VALUE;
      if (messages.messages.isEmpty()) {
        if (load_type == 0) {
          doneHolesInTable("messages_holes",dialog_id,max_id);
          doneHolesInMedia(dialog_id,max_id,-1);
        }
        return;
      }
      database.beginTransaction();
      if (load_type == 0) {
        int minId=messages.messages.get(messages.messages.size() - 1).id;
        closeHolesInTable("messages_holes",dialog_id,minId,max_id);
        closeHolesInMedia(dialog_id,minId,max_id,-1);
      }
 else       if (load_type == 1) {
        int maxId=messages.messages.get(0).id;
        closeHolesInTable("messages_holes",dialog_id,max_id,maxId);
        closeHolesInMedia(dialog_id,max_id,maxId,-1);
      }
 else       if (load_type == 3 || load_type == 2 || load_type == 4) {
        int maxId=max_id == 0 && load_type != 4 ? Integer.MAX_VALUE : messages.messages.get(0).id;
        int minId=messages.messages.get(messages.messages.size() - 1).id;
        closeHolesInTable("messages_holes",dialog_id,minId,maxId);
        closeHolesInMedia(dialog_id,minId,maxId,-1);
      }
      int count=messages.messages.size();
      SQLitePreparedStatement state_messages=database.executeFast("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
      SQLitePreparedStatement state_media=database.executeFast("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
      SQLitePreparedStatement state_polls=null;
      SQLitePreparedStatement state_webpage=null;
      TLRPC.Message botKeyboard=null;
      int minChannelMessageId=Integer.MAX_VALUE;
      int maxChannelMessageId=0;
      int channelId=0;
      for (int a=0; a < count; a++) {
        TLRPC.Message message=messages.messages.get(a);
        long messageId=message.id;
        if (channelId == 0) {
          channelId=message.to_id.channel_id;
        }
        if (message.to_id.channel_id != 0) {
          messageId|=((long)channelId) << 32;
        }
        if (load_type == -2) {
          SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT mid, data, ttl, mention, read_state, send_state FROM messages WHERE mid = %d",messageId));
          boolean exist;
          if (exist=cursor.next()) {
            NativeByteBuffer data=cursor.byteBufferValue(1);
            if (data != null) {
              TLRPC.Message oldMessage=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
              oldMessage.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
              data.reuse();
              int send_state=cursor.intValue(5);
              if (oldMessage != null && send_state != 3) {
                message.attachPath=oldMessage.attachPath;
                message.ttl=cursor.intValue(2);
              }
            }
            boolean oldMention=cursor.intValue(3) != 0;
            int readState=cursor.intValue(4);
            if (oldMention != message.mentioned) {
              if (mentionCountUpdate == Integer.MAX_VALUE) {
                SQLiteCursor cursor2=database.queryFinalized("SELECT unread_count_i FROM dialogs WHERE did = " + dialog_id);
                if (cursor2.next()) {
                  mentionCountUpdate=cursor2.intValue(0);
                }
                cursor2.dispose();
              }
              if (oldMention) {
                if (readState <= 1) {
                  mentionCountUpdate--;
                }
              }
 else {
                if (message.media_unread) {
                  mentionCountUpdate++;
                }
              }
            }
          }
          cursor.dispose();
          if (!exist) {
            continue;
          }
        }
        if (a == 0 && createDialog) {
          int pinned=0;
          int mentions=0;
          int flags=0;
          SQLiteCursor cursor=database.queryFinalized("SELECT pinned, unread_count_i, flags FROM dialogs WHERE did = " + dialog_id);
          boolean exist;
          if (exist=cursor.next()) {
            pinned=cursor.intValue(0);
            mentions=cursor.intValue(1);
            flags=cursor.intValue(2);
          }
          cursor.dispose();
          SQLitePreparedStatement state3;
          if (exist) {
            state3=database.executeFast("UPDATE dialogs SET date = ?, last_mid = ?, inbox_max = ?, last_mid_i = ?, pts = ?, date_i = ? WHERE did = ?");
            state3.bindInteger(1,message.date);
            state3.bindLong(2,messageId);
            state3.bindInteger(3,message.id);
            state3.bindLong(4,messageId);
            state3.bindInteger(5,messages.pts);
            state3.bindInteger(6,message.date);
            state3.bindLong(7,dialog_id);
          }
 else {
            state3=database.executeFast("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            state3.bindLong(1,dialog_id);
            state3.bindInteger(2,message.date);
            state3.bindInteger(3,0);
            state3.bindLong(4,messageId);
            state3.bindInteger(5,message.id);
            state3.bindInteger(6,0);
            state3.bindLong(7,messageId);
            state3.bindInteger(8,mentions);
            state3.bindInteger(9,messages.pts);
            state3.bindInteger(10,message.date);
            state3.bindInteger(11,pinned);
            state3.bindInteger(12,flags);
            state3.bindInteger(13,0);
            state3.bindNull(14);
          }
          state3.step();
          state3.dispose();
        }
        fixUnsupportedMedia(message);
        state_messages.requery();
        NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
        message.serializeToStream(data);
        state_messages.bindLong(1,messageId);
        state_messages.bindLong(2,dialog_id);
        state_messages.bindInteger(3,MessageObject.getUnreadFlags(message));
        state_messages.bindInteger(4,message.send_state);
        state_messages.bindInteger(5,message.date);
        state_messages.bindByteBuffer(6,data);
        state_messages.bindInteger(7,(MessageObject.isOut(message) ? 1 : 0));
        state_messages.bindInteger(8,message.ttl);
        if ((message.flags & TLRPC.MESSAGE_FLAG_HAS_VIEWS) != 0) {
          state_messages.bindInteger(9,message.views);
        }
 else {
          state_messages.bindInteger(9,getMessageMediaType(message));
        }
        state_messages.bindInteger(10,0);
        state_messages.bindInteger(11,message.mentioned ? 1 : 0);
        state_messages.step();
        if (DataQuery.canAddMessageToMedia(message)) {
          state_media.requery();
          state_media.bindLong(1,messageId);
          state_media.bindLong(2,dialog_id);
          state_media.bindInteger(3,message.date);
          state_media.bindInteger(4,DataQuery.getMediaType(message));
          state_media.bindByteBuffer(5,data);
          state_media.step();
        }
        data.reuse();
        if (message.media instanceof TLRPC.TL_messageMediaPoll) {
          if (state_polls == null) {
            state_polls=database.executeFast("REPLACE INTO polls VALUES(?, ?)");
          }
          TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)message.media;
          state_polls.requery();
          state_polls.bindLong(1,messageId);
          state_polls.bindLong(2,mediaPoll.poll.id);
          state_polls.step();
        }
 else         if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
          if (state_webpage == null) {
            state_webpage=database.executeFast("REPLACE INTO webpage_pending VALUES(?, ?)");
          }
          state_webpage.requery();
          state_webpage.bindLong(1,message.media.webpage.id);
          state_webpage.bindLong(2,messageId);
          state_webpage.step();
        }
        if (load_type == 0 && isValidKeyboardToSave(message)) {
          if (botKeyboard == null || botKeyboard.id < message.id) {
            botKeyboard=message;
          }
        }
      }
      state_messages.dispose();
      state_media.dispose();
      if (state_webpage != null) {
        state_webpage.dispose();
      }
      if (state_polls != null) {
        state_polls.dispose();
      }
      if (botKeyboard != null) {
        DataQuery.getInstance(currentAccount).putBotKeyboard(dialog_id,botKeyboard);
      }
      putUsersInternal(messages.users);
      putChatsInternal(messages.chats);
      if (mentionCountUpdate != Integer.MAX_VALUE) {
        database.executeFast(String.format(Locale.US,"UPDATE dialogs SET unread_count_i = %d WHERE did = %d",mentionCountUpdate,dialog_id)).stepThis().dispose();
        LongSparseArray<Integer> sparseArray=new LongSparseArray<>(1);
        sparseArray.put(dialog_id,mentionCountUpdate);
        MessagesController.getInstance(currentAccount).processDialogsUpdateRead(null,sparseArray);
      }
      database.commitTransaction();
      if (createDialog) {
        updateDialogsWithDeletedMessages(new ArrayList<>(),null,false,channelId);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
