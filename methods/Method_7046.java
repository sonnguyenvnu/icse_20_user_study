private void putDialogsInternal(final TLRPC.messages_Dialogs dialogs,int check){
  try {
    database.beginTransaction();
    final LongSparseArray<TLRPC.Message> new_dialogMessage=new LongSparseArray<>(dialogs.messages.size());
    for (int a=0; a < dialogs.messages.size(); a++) {
      TLRPC.Message message=dialogs.messages.get(a);
      new_dialogMessage.put(MessageObject.getDialogId(message),message);
    }
    if (!dialogs.dialogs.isEmpty()) {
      SQLitePreparedStatement state_messages=database.executeFast("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
      SQLitePreparedStatement state_dialogs=database.executeFast("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      SQLitePreparedStatement state_media=database.executeFast("REPLACE INTO media_v2 VALUES(?, ?, ?, ?, ?)");
      SQLitePreparedStatement state_settings=database.executeFast("REPLACE INTO dialog_settings VALUES(?, ?)");
      SQLitePreparedStatement state_holes=database.executeFast("REPLACE INTO messages_holes VALUES(?, ?, ?)");
      SQLitePreparedStatement state_media_holes=database.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
      SQLitePreparedStatement state_polls=null;
      for (int a=0; a < dialogs.dialogs.size(); a++) {
        TLRPC.Dialog dialog=dialogs.dialogs.get(a);
        DialogObject.initDialog(dialog);
        if (check == 1) {
          SQLiteCursor cursor=database.queryFinalized("SELECT did FROM dialogs WHERE did = " + dialog.id);
          boolean exists=cursor.next();
          cursor.dispose();
          if (exists) {
            continue;
          }
        }
 else         if (dialog.pinned && check == 2) {
          SQLiteCursor cursor=database.queryFinalized("SELECT pinned FROM dialogs WHERE did = " + dialog.id);
          if (cursor.next()) {
            dialog.pinnedNum=cursor.intValue(0);
          }
          cursor.dispose();
        }
        int messageDate=0;
        TLRPC.Message message=new_dialogMessage.get(dialog.id);
        if (message != null) {
          messageDate=Math.max(message.date,messageDate);
          if (isValidKeyboardToSave(message)) {
            DataQuery.getInstance(currentAccount).putBotKeyboard(dialog.id,message);
          }
          fixUnsupportedMedia(message);
          NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
          message.serializeToStream(data);
          long messageId=message.id;
          if (message.to_id.channel_id != 0) {
            messageId|=((long)message.to_id.channel_id) << 32;
          }
          state_messages.requery();
          state_messages.bindLong(1,messageId);
          state_messages.bindLong(2,dialog.id);
          state_messages.bindInteger(3,MessageObject.getUnreadFlags(message));
          state_messages.bindInteger(4,message.send_state);
          state_messages.bindInteger(5,message.date);
          state_messages.bindByteBuffer(6,data);
          state_messages.bindInteger(7,(MessageObject.isOut(message) ? 1 : 0));
          state_messages.bindInteger(8,0);
          state_messages.bindInteger(9,(message.flags & TLRPC.MESSAGE_FLAG_HAS_VIEWS) != 0 ? message.views : 0);
          state_messages.bindInteger(10,0);
          state_messages.bindInteger(11,message.mentioned ? 1 : 0);
          state_messages.step();
          if (DataQuery.canAddMessageToMedia(message)) {
            state_media.requery();
            state_media.bindLong(1,messageId);
            state_media.bindLong(2,dialog.id);
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
          createFirstHoles(dialog.id,state_holes,state_media_holes,message.id);
        }
        long topMessage=dialog.top_message;
        if (dialog.peer != null && dialog.peer.channel_id != 0) {
          topMessage|=((long)dialog.peer.channel_id) << 32;
        }
        state_dialogs.requery();
        state_dialogs.bindLong(1,dialog.id);
        state_dialogs.bindInteger(2,messageDate);
        state_dialogs.bindInteger(3,dialog.unread_count);
        state_dialogs.bindLong(4,topMessage);
        state_dialogs.bindInteger(5,dialog.read_inbox_max_id);
        state_dialogs.bindInteger(6,dialog.read_outbox_max_id);
        state_dialogs.bindLong(7,0);
        state_dialogs.bindInteger(8,dialog.unread_mentions_count);
        state_dialogs.bindInteger(9,dialog.pts);
        state_dialogs.bindInteger(10,0);
        state_dialogs.bindInteger(11,dialog.pinnedNum);
        int flags=0;
        if (dialog.unread_mark) {
          flags|=1;
        }
        state_dialogs.bindInteger(12,flags);
        state_dialogs.bindInteger(13,dialog.folder_id);
        NativeByteBuffer data;
        if (dialog instanceof TLRPC.TL_dialogFolder) {
          TLRPC.TL_dialogFolder dialogFolder=(TLRPC.TL_dialogFolder)dialog;
          data=new NativeByteBuffer(dialogFolder.folder.getObjectSize());
          dialogFolder.folder.serializeToStream(data);
          state_dialogs.bindByteBuffer(14,data);
        }
 else {
          data=null;
          state_dialogs.bindNull(14);
        }
        state_dialogs.step();
        if (data != null) {
          data.reuse();
        }
        if (dialog.notify_settings != null) {
          state_settings.requery();
          state_settings.bindLong(1,dialog.id);
          state_settings.bindInteger(2,dialog.notify_settings.mute_until != 0 ? 1 : 0);
          state_settings.step();
        }
      }
      state_messages.dispose();
      state_dialogs.dispose();
      state_media.dispose();
      state_settings.dispose();
      state_holes.dispose();
      state_media_holes.dispose();
      if (state_polls != null) {
        state_polls.dispose();
      }
    }
    putUsersInternal(dialogs.users);
    putChatsInternal(dialogs.chats);
    database.commitTransaction();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
