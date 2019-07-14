private ArrayList<Long> markMessagesAsDeletedInternal(final ArrayList<Integer> messages,int channelId){
  try {
    String ids;
    final ArrayList<Integer> temp=new ArrayList<>(messages);
    ArrayList<Long> dialogsIds=new ArrayList<>();
    LongSparseArray<Integer[]> dialogsToUpdate=new LongSparseArray<>();
    if (channelId != 0) {
      StringBuilder builder=new StringBuilder(messages.size());
      for (int a=0; a < messages.size(); a++) {
        long messageId=messages.get(a);
        messageId|=((long)channelId) << 32;
        if (builder.length() > 0) {
          builder.append(',');
        }
        builder.append(messageId);
      }
      ids=builder.toString();
    }
 else {
      ids=TextUtils.join(",",messages);
    }
    ArrayList<File> filesToDelete=new ArrayList<>();
    int currentUser=UserConfig.getInstance(currentAccount).getClientUserId();
    SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid, data, read_state, out, mention, mid FROM messages WHERE mid IN(%s)",ids));
    try {
      while (cursor.next()) {
        long did=cursor.longValue(0);
        int mid=cursor.intValue(5);
        temp.remove((Integer)mid);
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
    database.executeFast(String.format(Locale.US,"DELETE FROM messages WHERE mid IN(%s)",ids)).stepThis().dispose();
    database.executeFast(String.format(Locale.US,"DELETE FROM polls WHERE mid IN(%s)",ids)).stepThis().dispose();
    database.executeFast(String.format(Locale.US,"DELETE FROM bot_keyboard WHERE mid IN(%s)",ids)).stepThis().dispose();
    database.executeFast(String.format(Locale.US,"DELETE FROM messages_seq WHERE mid IN(%s)",ids)).stepThis().dispose();
    if (temp.isEmpty()) {
      cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid, type FROM media_v2 WHERE mid IN(%s)",ids));
      SparseArray<LongSparseArray<Integer>> mediaCounts=null;
      while (cursor.next()) {
        long uid=cursor.longValue(0);
        int type=cursor.intValue(1);
        if (mediaCounts == null) {
          mediaCounts=new SparseArray<>();
        }
        LongSparseArray<Integer> counts=mediaCounts.get(type);
        Integer count;
        if (counts == null) {
          counts=new LongSparseArray<>();
          count=0;
          mediaCounts.put(type,counts);
        }
 else {
          count=counts.get(uid);
        }
        if (count == null) {
          count=0;
        }
        count++;
        counts.put(uid,count);
      }
      cursor.dispose();
      if (mediaCounts != null) {
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO media_counts_v2 VALUES(?, ?, ?, ?)");
        for (int a=0; a < mediaCounts.size(); a++) {
          int type=mediaCounts.keyAt(a);
          LongSparseArray<Integer> value=mediaCounts.valueAt(a);
          for (int b=0; b < value.size(); b++) {
            long uid=value.keyAt(b);
            int lower_part=(int)uid;
            int count=-1;
            int old=0;
            cursor=database.queryFinalized(String.format(Locale.US,"SELECT count, old FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1",uid,type));
            if (cursor.next()) {
              count=cursor.intValue(0);
              old=cursor.intValue(1);
            }
            cursor.dispose();
            if (count != -1) {
              state.requery();
              count=Math.max(0,count - value.valueAt(b));
              state.bindLong(1,uid);
              state.bindInteger(2,type);
              state.bindInteger(3,count);
              state.bindInteger(4,old);
              state.step();
            }
          }
        }
        state.dispose();
      }
    }
 else {
      if (channelId == 0) {
        database.executeFast("UPDATE media_counts_v2 SET old = 1 WHERE 1").stepThis().dispose();
      }
 else {
        database.executeFast(String.format(Locale.US,"UPDATE media_counts_v2 SET old = 1 WHERE uid = %d",-channelId)).stepThis().dispose();
      }
    }
    database.executeFast(String.format(Locale.US,"DELETE FROM media_v2 WHERE mid IN(%s)",ids)).stepThis().dispose();
    DataQuery.getInstance(currentAccount).clearBotKeyboard(0,messages);
    return dialogsIds;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
