public void putChannelViews(final SparseArray<SparseIntArray> channelViews,final boolean isChannel){
  if (isEmpty(channelViews)) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("UPDATE messages SET media = max((SELECT media FROM messages WHERE mid = ?), ?) WHERE mid = ?");
      for (int a=0; a < channelViews.size(); a++) {
        int peer=channelViews.keyAt(a);
        SparseIntArray messages=channelViews.get(peer);
        for (int b=0; b < messages.size(); b++) {
          int views=messages.get(messages.keyAt(b));
          long messageId=messages.keyAt(b);
          if (isChannel) {
            messageId|=((long)-peer) << 32;
          }
          state.requery();
          state.bindLong(1,messageId);
          state.bindInteger(2,views);
          state.bindLong(3,messageId);
          state.step();
        }
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
