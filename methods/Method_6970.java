public void putBlockedUsers(final SparseIntArray ids,final boolean replace){
  if (ids == null || ids.size() == 0) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      if (replace) {
        database.executeFast("DELETE FROM blocked_users WHERE 1").stepThis().dispose();
      }
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO blocked_users VALUES(?)");
      for (int a=0, size=ids.size(); a < size; a++) {
        state.requery();
        state.bindInteger(1,ids.keyAt(a));
        state.step();
      }
      state.dispose();
      database.commitTransaction();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
