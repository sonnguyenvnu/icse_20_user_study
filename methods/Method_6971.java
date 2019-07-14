public void deleteUserChannelHistory(final int channelId,final int uid){
  storageQueue.postRunnable(() -> {
    try {
      long did=-channelId;
      final ArrayList<Integer> mids=new ArrayList<>();
      SQLiteCursor cursor=database.queryFinalized("SELECT data FROM messages WHERE uid = " + did);
      ArrayList<File> filesToDelete=new ArrayList<>();
      try {
        while (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
            data.reuse();
            if (message != null && message.from_id == uid && message.id != 1) {
              mids.add(message.id);
              if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
                for (int a=0, N=message.media.photo.sizes.size(); a < N; a++) {
                  TLRPC.PhotoSize photoSize=message.media.photo.sizes.get(a);
                  File file=FileLoader.getPathToAttach(photoSize);
                  if (file != null && file.toString().length() > 0) {
                    filesToDelete.add(file);
                  }
                }
              }
 else               if (message.media instanceof TLRPC.TL_messageMediaDocument) {
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
 catch (      Exception e) {
        FileLog.e(e);
      }
      cursor.dispose();
      AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).markChannelDialogMessageAsDeleted(mids,channelId));
      markMessagesAsDeletedInternal(mids,channelId);
      updateDialogsWithDeletedMessagesInternal(mids,null,channelId);
      FileLoader.getInstance(currentAccount).deleteFiles(filesToDelete,0);
      if (!mids.isEmpty()) {
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesDeleted,mids,channelId));
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
