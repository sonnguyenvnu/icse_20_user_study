private void getMediaCountDatabase(final long uid,final int type,final int classGuid){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      int count=-1;
      int old=0;
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT count, old FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1",uid,type));
      if (cursor.next()) {
        count=cursor.intValue(0);
        old=cursor.intValue(1);
      }
      cursor.dispose();
      int lower_part=(int)uid;
      if (count == -1 && lower_part == 0) {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT COUNT(mid) FROM media_v2 WHERE uid = %d AND type = %d LIMIT 1",uid,type));
        if (cursor.next()) {
          count=cursor.intValue(0);
        }
        cursor.dispose();
        if (count != -1) {
          putMediaCountDatabase(uid,type,count);
        }
      }
      processLoadedMediaCount(count,uid,type,classGuid,true,old);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
