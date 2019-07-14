public void updateChatDefaultBannedRights(int chatId,TLRPC.TL_chatBannedRights rights,int version){
  if (rights == null || chatId == 0) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      TLRPC.Chat chat=null;
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data FROM chats WHERE uid = %d",chatId));
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          chat=TLRPC.Chat.TLdeserialize(data,data.readInt32(false),false);
          data.reuse();
        }
      }
      cursor.dispose();
      if (chat == null || chat.default_banned_rights != null && version < chat.version) {
        return;
      }
      chat.default_banned_rights=rights;
      chat.flags|=262144;
      chat.version=version;
      SQLitePreparedStatement state=database.executeFast("UPDATE chats SET data = ? WHERE uid = ?");
      NativeByteBuffer data=new NativeByteBuffer(chat.getObjectSize());
      chat.serializeToStream(data);
      state.bindByteBuffer(1,data);
      state.bindInteger(2,chat.id);
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
