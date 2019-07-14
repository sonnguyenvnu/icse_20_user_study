private void savePeer(final int did,final int type,final double rating){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO chat_hints VALUES(?, ?, ?, ?)");
      state.requery();
      state.bindInteger(1,did);
      state.bindInteger(2,type);
      state.bindDouble(3,rating);
      state.bindInteger(4,(int)System.currentTimeMillis() / 1000);
      state.step();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
