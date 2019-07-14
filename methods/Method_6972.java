public void deleteDialog(final long did,final int messagesOnly){
  storageQueue.postRunnable(() -> {
    try {
      if (messagesOnly == 3) {
        int lastMid=-1;
        SQLiteCursor cursor=database.queryFinalized("SELECT last_mid FROM dialogs WHERE did = " + did);
        if (cursor.next()) {
          lastMid=cursor.intValue(0);
        }
        cursor.dispose();
        if (lastMid != 0) {
          return;
        }
      }
      if ((int)did == 0 || messagesOnly == 2) {
        SQLiteCursor cursor=database.queryFinalized("SELECT data FROM messages WHERE uid = " + did);
        ArrayList<File> filesToDelete=new ArrayList<>();
        try {
          while (cursor.next()) {
            NativeByteBuffer data=cursor.byteBufferValue(0);
            if (data != null) {
              TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
              message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
              data.reuse();
              if (message != null && message.media != null) {
                if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
                  for (int a=0, N=message.media.photo.sizes.size(); a < N; a++) {
                    TLRPC.PhotoSize photoSize=message.media.photo.sizes.get(a);
                    File file=FileLoader.getPathToAttach(photoSize);
                    if (file != null && file.toString().length() > 0) {
                      filesToDelete.add(file);
                    }
                  }
                }
 else                 if (message.media instanceof TLRPC.TL_messageMediaDocument) {
                  File file=FileLoader.getPathToAttach(message.media.document);
                  if (file != null && file.toString().length() > 0) {
                    filesToDelete.add(file);
                  }
                  for (int a=0, N=message.media.document.thumbs.size(); a < N; a++) {
                    TLRPC.PhotoSize photoSize=message.media.document.thumbs.get(a);
                    file=FileLoader.getPathToAttach(photoSize);
                    if (file != null && file.toString().length() > 0) {
                      filesToDelete.add(file);
                    }
                  }
                }
              }
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        cursor.dispose();
        FileLoader.getInstance(currentAccount).deleteFiles(filesToDelete,messagesOnly);
      }
      if (messagesOnly == 0 || messagesOnly == 3) {
        database.executeFast("DELETE FROM dialogs WHERE did = " + did).stepThis().dispose();
        database.executeFast("DELETE FROM chat_settings_v2 WHERE uid = " + did).stepThis().dispose();
        database.executeFast("DELETE FROM chat_pinned WHERE uid = " + did).stepThis().dispose();
        database.executeFast("DELETE FROM channel_users_v2 WHERE did = " + did).stepThis().dispose();
        database.executeFast("DELETE FROM search_recent WHERE did = " + did).stepThis().dispose();
        int lower_id=(int)did;
        int high_id=(int)(did >> 32);
        if (lower_id != 0) {
          if (high_id == 1) {
            database.executeFast("DELETE FROM chats WHERE uid = " + lower_id).stepThis().dispose();
          }
 else           if (lower_id < 0) {
          }
        }
 else {
          database.executeFast("DELETE FROM enc_chats WHERE uid = " + high_id).stepThis().dispose();
        }
      }
 else       if (messagesOnly == 2) {
        SQLiteCursor cursor=database.queryFinalized("SELECT last_mid_i, last_mid FROM dialogs WHERE did = " + did);
        int messageId=-1;
        if (cursor.next()) {
          long last_mid_i=cursor.longValue(0);
          long last_mid=cursor.longValue(1);
          SQLiteCursor cursor2=database.queryFinalized("SELECT data FROM messages WHERE uid = " + did + " AND mid IN (" + last_mid_i + "," + last_mid + ")");
          try {
            while (cursor2.next()) {
              NativeByteBuffer data=cursor2.byteBufferValue(0);
              if (data != null) {
                TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
                message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
                data.reuse();
                if (message != null) {
                  messageId=message.id;
                }
              }
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          cursor2.dispose();
          database.executeFast("DELETE FROM messages WHERE uid = " + did + " AND mid != " + last_mid_i + " AND mid != " + last_mid).stepThis().dispose();
          database.executeFast("DELETE FROM messages_holes WHERE uid = " + did).stepThis().dispose();
          database.executeFast("DELETE FROM bot_keyboard WHERE uid = " + did).stepThis().dispose();
          database.executeFast("DELETE FROM media_counts_v2 WHERE uid = " + did).stepThis().dispose();
          database.executeFast("DELETE FROM media_v2 WHERE uid = " + did).stepThis().dispose();
          database.executeFast("DELETE FROM media_holes_v2 WHERE uid = " + did).stepThis().dispose();
          DataQuery.getInstance(currentAccount).clearBotKeyboard(did,null);
          SQLitePreparedStatement state5=database.executeFast("REPLACE INTO messages_holes VALUES(?, ?, ?)");
          SQLitePreparedStatement state6=database.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
          if (messageId != -1) {
            createFirstHoles(did,state5,state6,messageId);
          }
          state5.dispose();
          state6.dispose();
        }
        cursor.dispose();
        return;
      }
      database.executeFast("UPDATE dialogs SET unread_count = 0, unread_count_i = 0 WHERE did = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM messages WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM bot_keyboard WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM media_counts_v2 WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM media_v2 WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM messages_holes WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM media_holes_v2 WHERE uid = " + did).stepThis().dispose();
      DataQuery.getInstance(currentAccount).clearBotKeyboard(did,null);
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
