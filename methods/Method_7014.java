public void getChatsInternal(String chatsToLoad,ArrayList<TLRPC.Chat> result) throws Exception {
  if (chatsToLoad == null || chatsToLoad.length() == 0 || result == null) {
    return;
  }
  SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data FROM chats WHERE uid IN(%s)",chatsToLoad));
  while (cursor.next()) {
    try {
      NativeByteBuffer data=cursor.byteBufferValue(0);
      if (data != null) {
        TLRPC.Chat chat=TLRPC.Chat.TLdeserialize(data,data.readInt32(false),false);
        data.reuse();
        if (chat != null) {
          result.add(chat);
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  cursor.dispose();
}
