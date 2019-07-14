private void putMessagesInternal(final ArrayList<TLRPC.Message> messages,final boolean withTransaction,final boolean doNotUpdateDialogDate,final int downloadMask,boolean ifNoLastMessage){
  try {
    if (ifNoLastMessage) {
      TLRPC.Message lastMessage=messages.get(0);
      if (lastMessage.dialog_id == 0) {
        MessageObject.getDialogId(lastMessage);
      }
      int lastMid=-1;
      SQLiteCursor cursor=database.queryFinalized("SELECT last_mid FROM dialogs WHERE did = " + lastMessage.dialog_id);
      if (cursor.next()) {
        lastMid=cursor.intValue(0);
      }
      cursor.dispose();
      if (lastMid != 0) {
        return;
      }
    }
    if (withTransaction) {
      database.beginTransaction();
    }
    LongSparseArray<TLRPC.Message> messagesMap=new LongSparseArray<>();
    LongSparseArray<Integer> messagesCounts=new LongSparseArray<>();
    LongSparseArray<Integer> mentionCounts=new LongSparseArray<>();
    SparseArray<LongSparseArray<Integer>> mediaCounts=null;
    LongSparseArray<TLRPC.Message> botKeyboards=new LongSparseArray<>();
    LongSparseArray<Long> messagesMediaIdsMap=null;
    LongSparseArray<Integer> mediaTypesChange=null;
    StringBuilder messageMediaIds=null;
    LongSparseArray<Integer> mediaTypes=null;
    StringBuilder messageIds=new StringBuilder();
    LongSparseArray<Integer> dialogsReadMax=new LongSparseArray<>();
    LongSparseArray<Long> messagesIdsMap=new LongSparseArray<>();
    LongSparseArray<Long> mentionsIdsMap=new LongSparseArray<>();
    SQLitePreparedStatement state_messages=database.executeFast("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
    SQLitePreparedStatement state_media=null;
    SQLitePreparedStatement state_randoms=database.executeFast("REPLACE INTO randoms VALUES(?, ?)");
    SQLitePreparedStatement state_download=database.executeFast("REPLACE INTO download_queue VALUES(?, ?, ?, ?, ?)");
    SQLitePreparedStatement state_webpage=database.executeFast("REPLACE INTO webpage_pending VALUES(?, ?)");
    SQLitePreparedStatement state_polls=null;
    for (int a=0; a < messages.size(); a++) {
      TLRPC.Message message=messages.get(a);
      long messageId=message.id;
      if (message.dialog_id == 0) {
        MessageObject.getDialogId(message);
      }
      if (message.to_id.channel_id != 0) {
        messageId|=((long)message.to_id.channel_id) << 32;
      }
      if (message.mentioned && message.media_unread) {
        mentionsIdsMap.put(messageId,message.dialog_id);
      }
      if (!(message.action instanceof TLRPC.TL_messageActionHistoryClear) && !MessageObject.isOut(message) && (message.id > 0 || MessageObject.isUnread(message))) {
        Integer currentMaxId=dialogsReadMax.get(message.dialog_id);
        if (currentMaxId == null) {
          SQLiteCursor cursor=database.queryFinalized("SELECT inbox_max FROM dialogs WHERE did = " + message.dialog_id);
          if (cursor.next()) {
            currentMaxId=cursor.intValue(0);
          }
 else {
            currentMaxId=0;
          }
          cursor.dispose();
          dialogsReadMax.put(message.dialog_id,currentMaxId);
        }
        if (message.id < 0 || currentMaxId < message.id) {
          if (messageIds.length() > 0) {
            messageIds.append(",");
          }
          messageIds.append(messageId);
          messagesIdsMap.put(messageId,message.dialog_id);
        }
      }
      if (DataQuery.canAddMessageToMedia(message)) {
        if (messageMediaIds == null) {
          messageMediaIds=new StringBuilder();
          messagesMediaIdsMap=new LongSparseArray<>();
          mediaTypes=new LongSparseArray<>();
        }
        if (messageMediaIds.length() > 0) {
          messageMediaIds.append(",");
        }
        messageMediaIds.append(messageId);
        messagesMediaIdsMap.put(messageId,message.dialog_id);
        mediaTypes.put(messageId,DataQuery.getMediaType(message));
      }
      if (isValidKeyboardToSave(message)) {
        TLRPC.Message oldMessage=botKeyboards.get(message.dialog_id);
        if (oldMessage == null || oldMessage.id < message.id) {
          botKeyboards.put(message.dialog_id,message);
        }
      }
    }
    for (int a=0; a < botKeyboards.size(); a++) {
      DataQuery.getInstance(currentAccount).putBotKeyboard(botKeyboards.keyAt(a),botKeyboards.valueAt(a));
    }
    if (messageMediaIds != null) {
      SQLiteCursor cursor=database.queryFinalized("SELECT mid, type FROM media_v2 WHERE mid IN(" + messageMediaIds.toString() + ")");
      while (cursor.next()) {
        long mid=cursor.longValue(0);
        int type=cursor.intValue(1);
        if (type == mediaTypes.get(mid)) {
          messagesMediaIdsMap.remove(mid);
        }
 else {
          if (mediaTypesChange == null) {
            mediaTypesChange=new LongSparseArray<>();
          }
          mediaTypesChange.put(mid,type);
        }
      }
      cursor.dispose();
      mediaCounts=new SparseArray<>();
      for (int a=0; a < messagesMediaIdsMap.size(); a++) {
        long key=messagesMediaIdsMap.keyAt(a);
        long value=messagesMediaIdsMap.valueAt(a);
        Integer type=mediaTypes.get(key);
        LongSparseArray<Integer> counts=mediaCounts.get(type);
        Integer count;
        if (counts == null) {
          counts=new LongSparseArray<>();
          count=0;
          mediaCounts.put(type,counts);
        }
 else {
          count=counts.get(value);
        }
        if (count == null) {
          count=0;
        }
        count++;
        counts.put(value,count);
        if (mediaTypesChange != null) {
          int previousType=mediaTypesChange.get(key,-1);
          if (previousType >= 0) {
            counts=mediaCounts.get(previousType);
            if (counts == null) {
              counts=new LongSparseArray<>();
              count=0;
              mediaCounts.put(previousType,counts);
            }
 else {
              count=counts.get(value);
            }
            if (count == null) {
              count=0;
            }
            count--;
            counts.put(value,count);
          }
        }
      }
    }
    if (messageIds.length() > 0) {
      SQLiteCursor cursor=database.queryFinalized("SELECT mid FROM messages WHERE mid IN(" + messageIds.toString() + ")");
      while (cursor.next()) {
        long mid=cursor.longValue(0);
        messagesIdsMap.remove(mid);
        mentionsIdsMap.remove(mid);
      }
      cursor.dispose();
      for (int a=0; a < messagesIdsMap.size(); a++) {
        long dialog_id=messagesIdsMap.valueAt(a);
        Integer count=messagesCounts.get(dialog_id);
        if (count == null) {
          count=0;
        }
        count++;
        messagesCounts.put(dialog_id,count);
      }
      for (int a=0; a < mentionsIdsMap.size(); a++) {
        long dialog_id=mentionsIdsMap.valueAt(a);
        Integer count=mentionCounts.get(dialog_id);
        if (count == null) {
          count=0;
        }
        count++;
        mentionCounts.put(dialog_id,count);
      }
    }
    int downloadMediaMask=0;
    for (int a=0; a < messages.size(); a++) {
      TLRPC.Message message=messages.get(a);
      fixUnsupportedMedia(message);
      state_messages.requery();
      long messageId=message.id;
      if (message.local_id != 0) {
        messageId=message.local_id;
      }
      if (message.to_id.channel_id != 0) {
        messageId|=((long)message.to_id.channel_id) << 32;
      }
      NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
      message.serializeToStream(data);
      boolean updateDialog=true;
      if (message.action instanceof TLRPC.TL_messageEncryptedAction && !(message.action.encryptedAction instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL || message.action.encryptedAction instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages)) {
        updateDialog=false;
      }
      if (updateDialog) {
        TLRPC.Message lastMessage=messagesMap.get(message.dialog_id);
        if (lastMessage == null || message.date > lastMessage.date || lastMessage.id > 0 && message.id > lastMessage.id || lastMessage.id < 0 && message.id < lastMessage.id) {
          messagesMap.put(message.dialog_id,message);
        }
      }
      state_messages.bindLong(1,messageId);
      state_messages.bindLong(2,message.dialog_id);
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
      if (message.random_id != 0) {
        state_randoms.requery();
        state_randoms.bindLong(1,message.random_id);
        state_randoms.bindLong(2,messageId);
        state_randoms.step();
      }
      if (DataQuery.canAddMessageToMedia(message)) {
        if (state_media == null) {
          state_media=database.executeFast("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
        }
        state_media.requery();
        state_media.bindLong(1,messageId);
        state_media.bindLong(2,message.dialog_id);
        state_media.bindInteger(3,message.date);
        state_media.bindInteger(4,DataQuery.getMediaType(message));
        state_media.bindByteBuffer(5,data);
        state_media.step();
      }
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
 else       if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
        state_webpage.requery();
        state_webpage.bindLong(1,message.media.webpage.id);
        state_webpage.bindLong(2,messageId);
        state_webpage.step();
      }
      data.reuse();
      if (downloadMask != 0 && (message.to_id.channel_id == 0 || message.post) && message.date >= ConnectionsManager.getInstance(currentAccount).getCurrentTime() - 60 * 60 && DownloadController.getInstance(currentAccount).canDownloadMedia(message) == 1) {
        if (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument || message.media instanceof TLRPC.TL_messageMediaWebPage) {
          int type=0;
          long id=0;
          TLRPC.MessageMedia object=null;
          TLRPC.Document document=MessageObject.getDocument(message);
          TLRPC.Photo photo=MessageObject.getPhoto(message);
          if (MessageObject.isVoiceMessage(message)) {
            id=document.id;
            type=DownloadController.AUTODOWNLOAD_TYPE_AUDIO;
            object=new TLRPC.TL_messageMediaDocument();
            object.document=document;
            object.flags|=1;
          }
 else           if (MessageObject.isStickerMessage(message)) {
            id=document.id;
            type=DownloadController.AUTODOWNLOAD_TYPE_PHOTO;
            object=new TLRPC.TL_messageMediaDocument();
            object.document=document;
            object.flags|=1;
          }
 else           if (MessageObject.isVideoMessage(message) || MessageObject.isRoundVideoMessage(message) || MessageObject.isGifMessage(message)) {
            id=document.id;
            type=DownloadController.AUTODOWNLOAD_TYPE_VIDEO;
            object=new TLRPC.TL_messageMediaDocument();
            object.document=document;
            object.flags|=1;
          }
 else           if (document != null) {
            id=document.id;
            type=DownloadController.AUTODOWNLOAD_TYPE_DOCUMENT;
            object=new TLRPC.TL_messageMediaDocument();
            object.document=document;
            object.flags|=1;
          }
 else           if (photo != null) {
            TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,AndroidUtilities.getPhotoSize());
            if (photoSize != null) {
              id=photo.id;
              type=DownloadController.AUTODOWNLOAD_TYPE_PHOTO;
              object=new TLRPC.TL_messageMediaPhoto();
              object.photo=photo;
              object.flags|=1;
              if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
                object.flags|=0x80000000;
              }
            }
          }
          if (object != null) {
            if (message.media.ttl_seconds != 0) {
              object.ttl_seconds=message.media.ttl_seconds;
              object.flags|=4;
            }
            downloadMediaMask|=type;
            state_download.requery();
            data=new NativeByteBuffer(object.getObjectSize());
            object.serializeToStream(data);
            state_download.bindLong(1,id);
            state_download.bindInteger(2,type);
            state_download.bindInteger(3,message.date);
            state_download.bindByteBuffer(4,data);
            state_download.bindString(5,"sent_" + (message.to_id != null ? message.to_id.channel_id : 0) + "_" + message.id);
            state_download.step();
            data.reuse();
          }
        }
      }
    }
    state_messages.dispose();
    if (state_media != null) {
      state_media.dispose();
    }
    if (state_polls != null) {
      state_polls.dispose();
    }
    state_randoms.dispose();
    state_download.dispose();
    state_webpage.dispose();
    SQLitePreparedStatement state_dialogs_replace=database.executeFast("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    SQLitePreparedStatement state_dialogs_update=database.executeFast("UPDATE dialogs SET date = ?, unread_count = ?, last_mid = ?, unread_count_i = ? WHERE did = ?");
    for (int a=0; a < messagesMap.size(); a++) {
      long key=messagesMap.keyAt(a);
      if (key == 0) {
        continue;
      }
      TLRPC.Message message=messagesMap.valueAt(a);
      int channelId=0;
      if (message != null) {
        channelId=message.to_id.channel_id;
      }
      SQLiteCursor cursor=database.queryFinalized("SELECT date, unread_count, last_mid, unread_count_i FROM dialogs WHERE did = " + key);
      int dialog_date=0;
      int last_mid=0;
      int old_unread_count=0;
      int old_mentions_count=0;
      boolean exists;
      if (exists=cursor.next()) {
        dialog_date=cursor.intValue(0);
        old_unread_count=Math.max(0,cursor.intValue(1));
        last_mid=cursor.intValue(2);
        old_mentions_count=Math.max(0,cursor.intValue(3));
      }
 else       if (channelId != 0) {
        MessagesController.getInstance(currentAccount).checkChannelInviter(channelId);
      }
      cursor.dispose();
      Integer mentions_count=mentionCounts.get(key);
      Integer unread_count=messagesCounts.get(key);
      if (unread_count == null) {
        unread_count=0;
      }
 else {
        messagesCounts.put(key,unread_count + old_unread_count);
      }
      if (mentions_count == null) {
        mentions_count=0;
      }
 else {
        mentionCounts.put(key,mentions_count + old_mentions_count);
      }
      long messageId=message != null ? message.id : last_mid;
      if (message != null) {
        if (message.local_id != 0) {
          messageId=message.local_id;
        }
      }
      if (channelId != 0) {
        messageId|=((long)channelId) << 32;
      }
      if (exists) {
        state_dialogs_update.requery();
        state_dialogs_update.bindInteger(1,message != null && (!doNotUpdateDialogDate || dialog_date == 0) ? message.date : dialog_date);
        state_dialogs_update.bindInteger(2,old_unread_count + unread_count);
        state_dialogs_update.bindLong(3,messageId);
        state_dialogs_update.bindInteger(4,old_mentions_count + mentions_count);
        state_dialogs_update.bindLong(5,key);
        state_dialogs_update.step();
      }
 else {
        state_dialogs_replace.requery();
        state_dialogs_replace.bindLong(1,key);
        state_dialogs_replace.bindInteger(2,message != null && (!doNotUpdateDialogDate || dialog_date == 0) ? message.date : dialog_date);
        state_dialogs_replace.bindInteger(3,old_unread_count + unread_count);
        state_dialogs_replace.bindLong(4,messageId);
        state_dialogs_replace.bindInteger(5,0);
        state_dialogs_replace.bindInteger(6,0);
        state_dialogs_replace.bindLong(7,0);
        state_dialogs_replace.bindInteger(8,old_mentions_count + mentions_count);
        state_dialogs_replace.bindInteger(9,channelId != 0 ? 1 : 0);
        state_dialogs_replace.bindInteger(10,0);
        state_dialogs_replace.bindInteger(11,0);
        state_dialogs_replace.bindInteger(12,0);
        state_dialogs_replace.bindInteger(13,0);
        state_dialogs_replace.bindNull(14);
        state_dialogs_replace.step();
      }
    }
    state_dialogs_update.dispose();
    state_dialogs_replace.dispose();
    if (mediaCounts != null) {
      state_randoms=database.executeFast("REPLACE INTO media_counts_v2 VALUES(?, ?, ?, ?)");
      for (int a=0; a < mediaCounts.size(); a++) {
        int type=mediaCounts.keyAt(a);
        LongSparseArray<Integer> value=mediaCounts.valueAt(a);
        for (int b=0; b < value.size(); b++) {
          long uid=value.keyAt(b);
          int lower_part=(int)uid;
          int count=-1;
          int old=0;
          SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT count, old FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1",uid,type));
          if (cursor.next()) {
            count=cursor.intValue(0);
            old=cursor.intValue(1);
          }
          cursor.dispose();
          if (count != -1) {
            state_randoms.requery();
            count+=value.valueAt(b);
            state_randoms.bindLong(1,uid);
            state_randoms.bindInteger(2,type);
            state_randoms.bindInteger(3,Math.max(0,count));
            state_randoms.bindInteger(4,old);
            state_randoms.step();
          }
        }
      }
      state_randoms.dispose();
    }
    if (withTransaction) {
      database.commitTransaction();
    }
    MessagesController.getInstance(currentAccount).processDialogsUpdateRead(messagesCounts,mentionCounts);
    if (downloadMediaMask != 0) {
      final int downloadMediaMaskFinal=downloadMediaMask;
      AndroidUtilities.runOnUIThread(() -> DownloadController.getInstance(currentAccount).newDownloadObjectsAvailable(downloadMediaMaskFinal));
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
