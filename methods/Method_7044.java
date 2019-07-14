public void getDialogs(final int folderId,final int offset,final int count){
  storageQueue.postRunnable(() -> {
    TLRPC.messages_Dialogs dialogs=new TLRPC.TL_messages_dialogs();
    ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
    try {
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      usersToLoad.add(UserConfig.getInstance(currentAccount).getClientUserId());
      ArrayList<Integer> chatsToLoad=new ArrayList<>();
      ArrayList<Integer> encryptedToLoad=new ArrayList<>();
      ArrayList<Long> replyMessages=new ArrayList<>();
      LongSparseArray<TLRPC.Message> replyMessageOwners=new LongSparseArray<>();
      ArrayList<Integer> foldersToLoad=new ArrayList<>(2);
      foldersToLoad.add(folderId);
      for (int a=0; a < foldersToLoad.size(); a++) {
        int fid=foldersToLoad.get(a);
        int off;
        int cnt;
        if (a == 0) {
          off=offset;
          cnt=count;
        }
 else {
          off=0;
          cnt=50;
        }
        SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT d.did, d.last_mid, d.unread_count, d.date, m.data, m.read_state, m.mid, m.send_state, s.flags, m.date, d.pts, d.inbox_max, d.outbox_max, m.replydata, d.pinned, d.unread_count_i, d.flags, d.folder_id, d.data FROM dialogs as d LEFT JOIN messages as m ON d.last_mid = m.mid LEFT JOIN dialog_settings as s ON d.did = s.did WHERE d.folder_id = %d ORDER BY d.pinned DESC, d.date DESC LIMIT %d,%d",fid,off,cnt));
        while (cursor.next()) {
          long dialogId=cursor.longValue(0);
          TLRPC.Dialog dialog;
          if (DialogObject.isFolderDialogId(dialogId)) {
            TLRPC.TL_dialogFolder dialogFolder=new TLRPC.TL_dialogFolder();
            if (!cursor.isNull(18)) {
              NativeByteBuffer data=cursor.byteBufferValue(18);
              if (data != null) {
                dialogFolder.folder=TLRPC.TL_folder.TLdeserialize(data,data.readInt32(false),false);
              }
 else {
                dialogFolder.folder=new TLRPC.TL_folder();
                dialogFolder.folder.id=(int)dialogId;
              }
              data.reuse();
            }
            dialog=dialogFolder;
            if (a == 0) {
              foldersToLoad.add(dialogFolder.folder.id);
            }
          }
 else {
            dialog=new TLRPC.TL_dialog();
          }
          dialog.id=dialogId;
          dialog.top_message=cursor.intValue(1);
          dialog.unread_count=cursor.intValue(2);
          dialog.last_message_date=cursor.intValue(3);
          dialog.pts=cursor.intValue(10);
          dialog.flags=dialog.pts == 0 || (int)dialog.id > 0 ? 0 : 1;
          dialog.read_inbox_max_id=cursor.intValue(11);
          dialog.read_outbox_max_id=cursor.intValue(12);
          dialog.pinnedNum=cursor.intValue(14);
          dialog.pinned=dialog.pinnedNum != 0;
          dialog.unread_mentions_count=cursor.intValue(15);
          int dialog_flags=cursor.intValue(16);
          dialog.unread_mark=(dialog_flags & 1) != 0;
          long flags=cursor.longValue(8);
          int low_flags=(int)flags;
          dialog.notify_settings=new TLRPC.TL_peerNotifySettings();
          if ((low_flags & 1) != 0) {
            dialog.notify_settings.mute_until=(int)(flags >> 32);
            if (dialog.notify_settings.mute_until == 0) {
              dialog.notify_settings.mute_until=Integer.MAX_VALUE;
            }
          }
          dialog.folder_id=cursor.intValue(17);
          dialogs.dialogs.add(dialog);
          NativeByteBuffer data=cursor.byteBufferValue(4);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
            data.reuse();
            if (message != null) {
              MessageObject.setUnreadFlags(message,cursor.intValue(5));
              message.id=cursor.intValue(6);
              int date=cursor.intValue(9);
              if (date != 0) {
                dialog.last_message_date=date;
              }
              message.send_state=cursor.intValue(7);
              message.dialog_id=dialog.id;
              dialogs.messages.add(message);
              addUsersAndChatsFromMessage(message,usersToLoad,chatsToLoad);
              try {
                if (message.reply_to_msg_id != 0 && (message.action instanceof TLRPC.TL_messageActionPinMessage || message.action instanceof TLRPC.TL_messageActionPaymentSent || message.action instanceof TLRPC.TL_messageActionGameScore)) {
                  if (!cursor.isNull(13)) {
                    data=cursor.byteBufferValue(13);
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
                    replyMessageOwners.put(dialog.id,message);
                  }
                }
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
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
      }
      if (!replyMessages.isEmpty()) {
        SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data, mid, date, uid FROM messages WHERE mid IN(%s)",TextUtils.join(",",replyMessages)));
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
            TLRPC.Message owner=replyMessageOwners.get(message.dialog_id);
            if (owner != null) {
              owner.replyMessage=message;
              message.dialog_id=owner.dialog_id;
              if (MessageObject.isMegagroup(owner)) {
                owner.replyMessage.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
              }
            }
          }
        }
        cursor.dispose();
      }
      if (!encryptedToLoad.isEmpty()) {
        getEncryptedChatsInternal(TextUtils.join(",",encryptedToLoad),encryptedChats,usersToLoad);
      }
      if (!chatsToLoad.isEmpty()) {
        getChatsInternal(TextUtils.join(",",chatsToLoad),dialogs.chats);
      }
      if (!usersToLoad.isEmpty()) {
        getUsersInternal(TextUtils.join(",",usersToLoad),dialogs.users);
      }
      MessagesController.getInstance(currentAccount).processLoadedDialogs(dialogs,encryptedChats,folderId,offset,count,1,false,false,true);
    }
 catch (    Exception e) {
      dialogs.dialogs.clear();
      dialogs.users.clear();
      dialogs.chats.clear();
      encryptedChats.clear();
      FileLog.e(e);
      MessagesController.getInstance(currentAccount).processLoadedDialogs(dialogs,encryptedChats,folderId,0,100,1,true,false,true);
    }
  }
);
}
