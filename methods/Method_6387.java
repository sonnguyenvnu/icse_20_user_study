private void savePinnedMessage(final TLRPC.Message result){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      long dialogId;
      if (result.to_id.channel_id != 0) {
        dialogId=-result.to_id.channel_id;
      }
 else       if (result.to_id.chat_id != 0) {
        dialogId=-result.to_id.chat_id;
      }
 else       if (result.to_id.user_id != 0) {
        dialogId=result.to_id.user_id;
      }
 else {
        return;
      }
      MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
      SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO chat_pinned VALUES(?, ?, ?)");
      NativeByteBuffer data=new NativeByteBuffer(result.getObjectSize());
      result.serializeToStream(data);
      state.requery();
      state.bindLong(1,dialogId);
      state.bindInteger(2,result.id);
      state.bindByteBuffer(3,data);
      state.step();
      data.reuse();
      state.dispose();
      MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
