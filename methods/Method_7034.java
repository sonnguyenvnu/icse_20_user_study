private ArrayList<Long> markMessagesAsDeletedInternal(final int channelId,final int mid){
  try {
    String ids;
    ArrayList<Long> dialogsIds=new ArrayList<>();
    LongSparseArray<Integer[]> dialogsToUpdate=new LongSparseArray<>();
    long maxMessageId=mid;
    maxMessageId|=((long)channelId) << 32;
    ArrayList<File> filesToDelete=new ArrayList<>();
    int currentUser=UserConfig.getInstance(currentAccount).getClientUserId();
    SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid, data, read_state, out, mention FROM messages WHERE uid = %d AND mid <= %d",-channelId,maxMessageId));
    try {
      while (cursor.next()) {
        long did=cursor.longValue(0);
        if (did == currentUser) {
          continue;
        }
        int read_state=cursor.intValue(2);
        if (cursor.intValue(3) == 0) {
          Integer[] unread_count=dialogsToUpdate.get(did);
          if (unread_count == null) {
            unread_count=new Integer[]{0,0};
            dialogsToUpdate.put(did,unread_count);
          }
          if (read_state < 2) {
            unread_count[1]++;
          }
          if (read_state == 0 || read_state == 2) {
            unread_count[0]++;
          }
        }
        if ((int)did != 0) {
          continue;
        }
        NativeByteBuffer data=cursor.byteBufferValue(1);
        if (data != null) {
          TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
          message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
          data.reuse();
          if (message != null) {
            if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
              for (int a=0, N=message.media.photo.sizes.size(); a < N; a++) {
                TLRPC.PhotoSize photoSize=message.media.photo.sizes.get(a);
                File file=FileLoader.getPathToAttach(photoSize);
                if (file != null && file.toString().length() > 0) {
                  filesToDelete.add(file);
                }
              }
            }
 else             if (message.media instanceof TLRPC.TL_messageMediaDocument) {
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
 catch (    Exception e) {
      FileLog.e(e);
    }
    cursor.dispose();
    FileLoader.getInstance(currentAccount).deleteFiles(filesToDelete,0);
    for (int a=0; a < dialogsToUpdate.size(); a++) {
      long did=dialogsToUpdate.keyAt(a);
      Integer[] counts=dialogsToUpdate.valueAt(a);
      cursor=database.queryFinalized("SELECT unread_count, unread_count_i FROM dialogs WHERE did = " + did);
      int old_unread_count=0;
      int old_mentions_count=0;
      if (cursor.next()) {
        old_unread_count=cursor.intValue(0);
        old_mentions_count=cursor.intValue(1);
      }
      cursor.dispose();
      dialogsIds.add(did);
      SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET unread_count = ?, unread_count_i = ? WHERE did = ?");
      state.requery();
      state.bindInteger(1,Math.max(0,old_unread_count - counts[0]));
      state.bindInteger(2,Math.max(0,old_mentions_count - counts[1]));
      state.bindLong(3,did);
      state.step();
      state.dispose();
    }
    database.executeFast(String.format(Locale.US,"DELETE FROM messages WHERE uid = %d AND mid <= %d",-channelId,maxMessageId)).stepThis().dispose();
    database.executeFast(String.format(Locale.US,"DELETE FROM media_v2 WHERE uid = %d AND mid <= %d",-channelId,maxMessageId)).stepThis().dispose();
    database.executeFast(String.format(Locale.US,"UPDATE media_counts_v2 SET old = 1 WHERE uid = %d",-channelId)).stepThis().dispose();
    return dialogsIds;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
