private void updateDialogsWithReadMessagesInternal(final ArrayList<Integer> messages,final SparseLongArray inbox,SparseLongArray outbox,final ArrayList<Long> mentions){
  try {
    LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>();
    LongSparseArray<Integer> dialogsToUpdateMentions=new LongSparseArray<>();
    ArrayList<Integer> channelMentionsToReload=new ArrayList<>();
    if (!isEmpty(messages)) {
      String ids=TextUtils.join(",",messages);
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid, read_state, out FROM messages WHERE mid IN(%s)",ids));
      while (cursor.next()) {
        int out=cursor.intValue(2);
        if (out != 0) {
          continue;
        }
        int read_state=cursor.intValue(1);
        if (read_state != 0) {
          continue;
        }
        long uid=cursor.longValue(0);
        Integer currentCount=dialogsToUpdate.get(uid);
        if (currentCount == null) {
          dialogsToUpdate.put(uid,1);
        }
 else {
          dialogsToUpdate.put(uid,currentCount + 1);
        }
      }
      cursor.dispose();
    }
 else {
      if (!isEmpty(inbox)) {
        for (int b=0; b < inbox.size(); b++) {
          int key=inbox.keyAt(b);
          long messageId=inbox.get(key);
          SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT COUNT(mid) FROM messages WHERE uid = %d AND mid > %d AND read_state IN(0,2) AND out = 0",key,messageId));
          if (cursor.next()) {
            dialogsToUpdate.put((long)key,cursor.intValue(0));
          }
          cursor.dispose();
          SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET inbox_max = max((SELECT inbox_max FROM dialogs WHERE did = ?), ?) WHERE did = ?");
          state.requery();
          state.bindLong(1,key);
          state.bindInteger(2,(int)messageId);
          state.bindLong(3,key);
          state.step();
          state.dispose();
        }
      }
      if (!isEmpty(mentions)) {
        ArrayList<Long> notFoundMentions=new ArrayList<>(mentions);
        String ids=TextUtils.join(",",mentions);
        SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid, read_state, out, mention, mid FROM messages WHERE mid IN(%s)",ids));
        while (cursor.next()) {
          long did=cursor.longValue(0);
          notFoundMentions.remove(cursor.longValue(4));
          if (cursor.intValue(1) < 2 && cursor.intValue(2) == 0 && cursor.intValue(3) == 1) {
            Integer unread_count=dialogsToUpdateMentions.get(did);
            if (unread_count == null) {
              SQLiteCursor cursor2=database.queryFinalized("SELECT unread_count_i FROM dialogs WHERE did = " + did);
              int old_mentions_count=0;
              if (cursor2.next()) {
                old_mentions_count=cursor2.intValue(0);
              }
              cursor2.dispose();
              dialogsToUpdateMentions.put(did,Math.max(0,old_mentions_count - 1));
            }
 else {
              dialogsToUpdateMentions.put(did,Math.max(0,unread_count - 1));
            }
          }
        }
        cursor.dispose();
        for (int a=0; a < notFoundMentions.size(); a++) {
          int channelId=(int)(notFoundMentions.get(a) >> 32);
          if (channelId > 0 && !channelMentionsToReload.contains(channelId)) {
            channelMentionsToReload.add(channelId);
          }
        }
      }
      if (!isEmpty(outbox)) {
        for (int b=0; b < outbox.size(); b++) {
          int key=outbox.keyAt(b);
          long messageId=outbox.get(key);
          SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET outbox_max = max((SELECT outbox_max FROM dialogs WHERE did = ?), ?) WHERE did = ?");
          state.requery();
          state.bindLong(1,key);
          state.bindInteger(2,(int)messageId);
          state.bindLong(3,key);
          state.step();
          state.dispose();
        }
      }
    }
    if (dialogsToUpdate.size() > 0 || dialogsToUpdateMentions.size() > 0) {
      database.beginTransaction();
      if (dialogsToUpdate.size() > 0) {
        SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET unread_count = ? WHERE did = ?");
        for (int a=0; a < dialogsToUpdate.size(); a++) {
          state.requery();
          state.bindInteger(1,dialogsToUpdate.valueAt(a));
          state.bindLong(2,dialogsToUpdate.keyAt(a));
          state.step();
        }
        state.dispose();
      }
      if (dialogsToUpdateMentions.size() > 0) {
        SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET unread_count_i = ? WHERE did = ?");
        for (int a=0; a < dialogsToUpdateMentions.size(); a++) {
          state.requery();
          state.bindInteger(1,dialogsToUpdateMentions.valueAt(a));
          state.bindLong(2,dialogsToUpdateMentions.keyAt(a));
          state.step();
        }
        state.dispose();
      }
      database.commitTransaction();
    }
    MessagesController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate,dialogsToUpdateMentions);
    if (!channelMentionsToReload.isEmpty()) {
      MessagesController.getInstance(currentAccount).reloadMentionsCountForChannels(channelMentionsToReload);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
