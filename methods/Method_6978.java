public void resetMentionsCount(final long did,final int count){
  storageQueue.postRunnable(() -> {
    try {
      if (count == 0) {
        database.executeFast(String.format(Locale.US,"UPDATE messages SET read_state = read_state | 2 WHERE uid = %d AND mention = 1 AND read_state IN(0, 1)",did)).stepThis().dispose();
      }
      database.executeFast(String.format(Locale.US,"UPDATE dialogs SET unread_count_i = %d WHERE did = %d",count,did)).stepThis().dispose();
      LongSparseArray<Integer> sparseArray=new LongSparseArray<>(1);
      sparseArray.put(did,count);
      MessagesController.getInstance(currentAccount).processDialogsUpdateRead(null,sparseArray);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
