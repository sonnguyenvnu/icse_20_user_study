public void getUnreadMention(final long dialog_id,final IntCallback callback){
  storageQueue.postRunnable(() -> {
    try {
      final int result;
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT MIN(mid) FROM messages WHERE uid = %d AND mention = 1 AND read_state IN(0, 1)",dialog_id));
      if (cursor.next()) {
        result=cursor.intValue(0);
      }
 else {
        result=0;
      }
      cursor.dispose();
      AndroidUtilities.runOnUIThread(() -> callback.run(result));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
