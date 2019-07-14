private void putMediaCountDatabase(final long uid,final int type,final int count){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      SQLitePreparedStatement state2=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO media_counts_v2 VALUES(?, ?, ?, ?)");
      state2.requery();
      state2.bindLong(1,uid);
      state2.bindInteger(2,type);
      state2.bindInteger(3,count);
      state2.bindInteger(4,0);
      state2.step();
      state2.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
