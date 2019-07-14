public void updateEncryptedChatTTL(final TLRPC.EncryptedChat chat){
  if (chat == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    SQLitePreparedStatement state=null;
    try {
      state=database.executeFast("UPDATE enc_chats SET ttl = ? WHERE uid = ?");
      state.bindInteger(1,chat.ttl);
      state.bindInteger(2,chat.id);
      state.step();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      if (state != null) {
        state.dispose();
      }
    }
  }
);
}
