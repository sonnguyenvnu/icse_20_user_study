public void saveBotCache(final String key,final TLObject result){
  if (result == null || TextUtils.isEmpty(key)) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      int currentDate=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
      if (result instanceof TLRPC.TL_messages_botCallbackAnswer) {
        currentDate+=((TLRPC.TL_messages_botCallbackAnswer)result).cache_time;
      }
 else       if (result instanceof TLRPC.TL_messages_botResults) {
        currentDate+=((TLRPC.TL_messages_botResults)result).cache_time;
      }
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO botcache VALUES(?, ?, ?)");
      NativeByteBuffer data=new NativeByteBuffer(result.getObjectSize());
      result.serializeToStream(data);
      state.bindString(1,key);
      state.bindInteger(2,currentDate);
      state.bindByteBuffer(3,data);
      state.step();
      state.dispose();
      data.reuse();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
