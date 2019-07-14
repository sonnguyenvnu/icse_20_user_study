public void putChannelAdmins(final int chatId,final ArrayList<Integer> ids){
  storageQueue.postRunnable(() -> {
    try {
      database.executeFast("DELETE FROM channel_admins WHERE did = " + chatId).stepThis().dispose();
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO channel_admins VALUES(?, ?)");
      int date=(int)(System.currentTimeMillis() / 1000);
      for (int a=0; a < ids.size(); a++) {
        state.requery();
        state.bindInteger(1,chatId);
        state.bindInteger(2,ids.get(a));
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
