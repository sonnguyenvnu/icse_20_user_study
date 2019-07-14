public void markMentionMessageAsRead(final int messageId,final int channelId,final long did){
  storageQueue.postRunnable(() -> {
    try {
      long mid=messageId;
      if (channelId != 0) {
        mid|=((long)channelId) << 32;
      }
      database.executeFast(String.format(Locale.US,"UPDATE messages SET read_state = read_state | 2 WHERE mid = %d",mid)).stepThis().dispose();
      SQLiteCursor cursor=database.queryFinalized("SELECT unread_count_i FROM dialogs WHERE did = " + did);
      int old_mentions_count=0;
      if (cursor.next()) {
        old_mentions_count=Math.max(0,cursor.intValue(0) - 1);
      }
      cursor.dispose();
      database.executeFast(String.format(Locale.US,"UPDATE dialogs SET unread_count_i = %d WHERE did = %d",old_mentions_count,did)).stepThis().dispose();
      LongSparseArray<Integer> sparseArray=new LongSparseArray<>(1);
      sparseArray.put(did,old_mentions_count);
      MessagesController.getInstance(currentAccount).processDialogsUpdateRead(null,sparseArray);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
