public void putBotInfo(final TLRPC.BotInfo botInfo){
  if (botInfo == null) {
    return;
  }
  botInfos.put(botInfo.user_id,botInfo);
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO bot_info(uid, info) VALUES(?, ?)");
      state.requery();
      NativeByteBuffer data=new NativeByteBuffer(botInfo.getObjectSize());
      botInfo.serializeToStream(data);
      state.bindInteger(1,botInfo.user_id);
      state.bindByteBuffer(2,data);
      state.step();
      data.reuse();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
