public boolean isMigratedChat(final int chat_id){
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  final boolean[] result=new boolean[1];
  storageQueue.postRunnable(() -> {
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT info FROM chat_settings_v2 WHERE uid = " + chat_id);
      TLRPC.ChatFull info=null;
      ArrayList<TLRPC.User> loadedUsers=new ArrayList<>();
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          info=TLRPC.ChatFull.TLdeserialize(data,data.readInt32(false),false);
          data.reuse();
        }
      }
      cursor.dispose();
      result[0]=info instanceof TLRPC.TL_channelFull && info.migrated_from_chat_id != 0;
      if (countDownLatch != null) {
        countDownLatch.countDown();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      if (countDownLatch != null) {
        countDownLatch.countDown();
      }
    }
  }
);
  try {
    countDownLatch.await();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return result[0];
}
