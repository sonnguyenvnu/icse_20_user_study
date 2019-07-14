public void emptyMessagesMedia(final ArrayList<Integer> mids){
  storageQueue.postRunnable(() -> {
    try {
      ArrayList<File> filesToDelete=new ArrayList<>();
      final ArrayList<TLRPC.Message> messages=new ArrayList<>();
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data, mid, date, uid FROM messages WHERE mid IN (%s)",TextUtils.join(",",mids)));
      while (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
          message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
          data.reuse();
          if (message.media != null) {
            if (message.media.document != null) {
              File file=FileLoader.getPathToAttach(message.media.document,true);
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
              message.media.document=new TLRPC.TL_documentEmpty();
            }
 else             if (message.media.photo != null) {
              for (int a=0, N=message.media.photo.sizes.size(); a < N; a++) {
                TLRPC.PhotoSize photoSize=message.media.photo.sizes.get(a);
                File file=FileLoader.getPathToAttach(photoSize);
                if (file != null && file.toString().length() > 0) {
                  filesToDelete.add(file);
                }
              }
              message.media.photo=new TLRPC.TL_photoEmpty();
            }
 else {
              continue;
            }
            message.media.flags=message.media.flags & ~1;
            message.id=cursor.intValue(1);
            message.date=cursor.intValue(2);
            message.dialog_id=cursor.longValue(3);
            messages.add(message);
          }
        }
      }
      cursor.dispose();
      if (!messages.isEmpty()) {
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)");
        for (int a=0; a < messages.size(); a++) {
          TLRPC.Message message=messages.get(a);
          NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
          message.serializeToStream(data);
          state.requery();
          state.bindLong(1,message.id);
          state.bindLong(2,message.dialog_id);
          state.bindInteger(3,MessageObject.getUnreadFlags(message));
          state.bindInteger(4,message.send_state);
          state.bindInteger(5,message.date);
          state.bindByteBuffer(6,data);
          state.bindInteger(7,(MessageObject.isOut(message) ? 1 : 0));
          state.bindInteger(8,message.ttl);
          if ((message.flags & TLRPC.MESSAGE_FLAG_HAS_VIEWS) != 0) {
            state.bindInteger(9,message.views);
          }
 else {
            state.bindInteger(9,getMessageMediaType(message));
          }
          state.bindInteger(10,0);
          state.bindInteger(11,message.mentioned ? 1 : 0);
          state.step();
          data.reuse();
        }
        state.dispose();
        AndroidUtilities.runOnUIThread(() -> {
          for (int a=0; a < messages.size(); a++) {
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateMessageMedia,messages.get(a));
          }
        }
);
      }
      FileLoader.getInstance(currentAccount).deleteFiles(filesToDelete,0);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
