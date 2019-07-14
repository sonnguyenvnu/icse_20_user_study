public void setDialogPinned(final long did,final int pinned){
  storageQueue.postRunnable(() -> {
    try {
      SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET pinned = ? WHERE did = ?");
      state.bindInteger(1,pinned);
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
