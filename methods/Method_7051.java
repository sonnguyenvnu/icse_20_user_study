public void setDialogUnread(final long did,final boolean unread){
  storageQueue.postRunnable(() -> {
    try {
      int flags=0;
      SQLiteCursor cursor=null;
      try {
        cursor=database.queryFinalized("SELECT flags FROM dialogs WHERE did = " + did);
        if (cursor.next()) {
          flags=cursor.intValue(0);
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
 finally {
        if (cursor != null) {
          cursor.dispose();
        }
      }
      if (unread) {
        flags|=1;
      }
 else {
        flags&=~1;
      }
      SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET flags = ? WHERE did = ?");
      state.bindInteger(1,flags);
      state.bindLong(2,did);
      state.step();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
