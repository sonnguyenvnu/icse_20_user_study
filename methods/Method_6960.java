public void saveChannelPts(final int channelId,final int pts){
  storageQueue.postRunnable(() -> {
    try {
      SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET pts = ? WHERE did = ?");
      state.bindInteger(1,pts);
      state.bindInteger(2,-channelId);
      state.step();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
