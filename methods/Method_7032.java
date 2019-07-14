private void updateDialogsWithDeletedMessagesInternal(final ArrayList<Integer> messages,ArrayList<Long> additionalDialogsToUpdate,int channelId){
  if (Thread.currentThread().getId() != storageQueue.getId()) {
    throw new RuntimeException("wrong db thread");
  }
  try {
    String ids;
    ArrayList<Long> dialogsToUpdate=new ArrayList<>();
    if (!messages.isEmpty()) {
      SQLitePreparedStatement state;
      if (channelId != 0) {
        dialogsToUpdate.add((long)-channelId);
        state=database.executeFast("UPDATE dialogs SET last_mid = (SELECT mid FROM messages WHERE uid = ? AND date = (SELECT MAX(date) FROM messages WHERE uid = ?)) WHERE did = ?");
      }
 else {
        ids=TextUtils.join(",",messages);
        SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT did FROM dialogs WHERE last_mid IN(%s)",ids));
        while (cursor.next()) {
          dialogsToUpdate.add(cursor.longValue(0));
        }
        cursor.dispose();
        state=database.executeFast("UPDATE dialogs SET last_mid = (SELECT mid FROM messages WHERE uid = ? AND date = (SELECT MAX(date) FROM messages WHERE uid = ? AND date != 0)) WHERE did = ?");
      }
      database.beginTransaction();
      for (int a=0; a < dialogsToUpdate.size(); a++) {
        long did=dialogsToUpdate.get(a);
        state.requery();
        state.bindLong(1,did);
        state.bindLong(2,did);
        state.bindLong(3,did);
        state.step();
      }
      state.dispose();
      database.commitTransaction();
    }
 else {
      dialogsToUpdate.add((long)-channelId);
    }
    if (additionalDialogsToUpdate != null) {
      for (int a=0; a < additionalDialogsToUpdate.size(); a++) {
        Long did=additionalDialogsToUpdate.get(a);
        if (!dialogsToUpdate.contains(did)) {
          dialogsToUpdate.add(did);
        }
      }
    }
    ids=TextUtils.join(",",dialogsToUpdate);
    TLRPC.messages_Dialogs dialogs=new TLRPC.TL_messages_dialogs();
    ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
    ArrayList<Integer> usersToLoad=new ArrayList<>();
    ArrayList<Integer> chatsToLoad=new ArrayList<>();
    ArrayList<Integer> encryptedToLoad=new ArrayList<>();
    SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT d.did, d.last_mid, d.unread_count, d.date, m.data, m.read_state, m.mid, m.send_state, m.date, d.pts, d.inbox_max, d.outbox_max, d.pinned, d.unread_count_i, d.flags, d.folder_id, d.data FROM dialogs as d LEFT JOIN messages as m ON d.last_mid = m.mid WHERE d.did IN(%s)",ids));
    while (cursor.next()) {
      long dialogId=cursor.longValue(0);
      TLRPC.Dialog dialog;
      if (DialogObject.isFolderDialogId(dialogId)) {
        TLRPC.TL_dialogFolder dialogFolder=new TLRPC.TL_dialogFolder();
        if (!cursor.isNull(16)) {
          NativeByteBuffer data=cursor.byteBufferValue(16);
          if (data != null) {
            dialogFolder.folder=TLRPC.TL_folder.TLdeserialize(data,data.readInt32(false),false);
          }
 else {
            dialogFolder.folder=new TLRPC.TL_folder();
            dialogFolder.folder.id=cursor.intValue(15);
          }
          data.reuse();
        }
        dialog=dialogFolder;
      }
 else {
        dialog=new TLRPC.TL_dialog();
      }
      dialog.id=dialogId;
      dialog.top_message=cursor.intValue(1);
      dialog.read_inbox_max_id=cursor.intValue(10);
      dialog.read_outbox_max_id=cursor.intValue(11);
      dialog.unread_count=cursor.intValue(2);
      dialog.unread_mentions_count=cursor.intValue(13);
      dialog.last_message_date=cursor.intValue(3);
      dialog.pts=cursor.intValue(9);
      dialog.flags=channelId == 0 ? 0 : 1;
      dialog.pinnedNum=cursor.intValue(12);
      dialog.pinned=dialog.pinnedNum != 0;
      int dialog_flags=cursor.intValue(14);
      dialog.unread_mark=(dialog_flags & 1) != 0;
      dialog.folder_id=cursor.intValue(15);
      dialogs.dialogs.add(dialog);
      NativeByteBuffer data=cursor.byteBufferValue(4);
      if (data != null) {
        TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
        message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
        data.reuse();
        MessageObject.setUnreadFlags(message,cursor.intValue(5));
        message.id=cursor.intValue(6);
        message.send_state=cursor.intValue(7);
        int date=cursor.intValue(8);
        if (date != 0) {
          dialog.last_message_date=date;
        }
        message.dialog_id=dialog.id;
        dialogs.messages.add(message);
        addUsersAndChatsFromMessage(message,usersToLoad,chatsToLoad);
      }
      int lower_id=(int)dialog.id;
      int high_id=(int)(dialog.id >> 32);
      if (lower_id != 0) {
        if (high_id == 1) {
          if (!chatsToLoad.contains(lower_id)) {
            chatsToLoad.add(lower_id);
          }
        }
 else {
          if (lower_id > 0) {
            if (!usersToLoad.contains(lower_id)) {
              usersToLoad.add(lower_id);
            }
          }
 else {
            if (!chatsToLoad.contains(-lower_id)) {
              chatsToLoad.add(-lower_id);
            }
          }
        }
      }
 else {
        if (!encryptedToLoad.contains(high_id)) {
          encryptedToLoad.add(high_id);
        }
      }
    }
    cursor.dispose();
    if (!encryptedToLoad.isEmpty()) {
      getEncryptedChatsInternal(TextUtils.join(",",encryptedToLoad),encryptedChats,usersToLoad);
    }
    if (!chatsToLoad.isEmpty()) {
      getChatsInternal(TextUtils.join(",",chatsToLoad),dialogs.chats);
    }
    if (!usersToLoad.isEmpty()) {
      getUsersInternal(TextUtils.join(",",usersToLoad),dialogs.users);
    }
    if (!dialogs.dialogs.isEmpty() || !encryptedChats.isEmpty()) {
      MessagesController.getInstance(currentAccount).processDialogsUpdate(dialogs,encryptedChats);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
