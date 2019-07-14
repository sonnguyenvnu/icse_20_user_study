private void putChatsInternal(ArrayList<TLRPC.Chat> chats) throws Exception {
  if (chats == null || chats.isEmpty()) {
    return;
  }
  SQLitePreparedStatement state=database.executeFast("REPLACE INTO chats VALUES(?, ?, ?)");
  for (int a=0; a < chats.size(); a++) {
    TLRPC.Chat chat=chats.get(a);
    if (chat.min) {
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data FROM chats WHERE uid = %d",chat.id));
      if (cursor.next()) {
        try {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLRPC.Chat oldChat=TLRPC.Chat.TLdeserialize(data,data.readInt32(false),false);
            data.reuse();
            if (oldChat != null) {
              oldChat.title=chat.title;
              oldChat.photo=chat.photo;
              oldChat.broadcast=chat.broadcast;
              oldChat.verified=chat.verified;
              oldChat.megagroup=chat.megagroup;
              if (chat.default_banned_rights != null) {
                oldChat.default_banned_rights=chat.default_banned_rights;
                oldChat.flags|=262144;
              }
              if (chat.admin_rights != null) {
                oldChat.admin_rights=chat.admin_rights;
                oldChat.flags|=16384;
              }
              if (chat.banned_rights != null) {
                oldChat.banned_rights=chat.banned_rights;
                oldChat.flags|=32768;
              }
              if (chat.username != null) {
                oldChat.username=chat.username;
                oldChat.flags|=64;
              }
 else {
                oldChat.username=null;
                oldChat.flags=oldChat.flags & ~64;
              }
              chat=oldChat;
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      cursor.dispose();
    }
    state.requery();
    NativeByteBuffer data=new NativeByteBuffer(chat.getObjectSize());
    chat.serializeToStream(data);
    state.bindInteger(1,chat.id);
    if (chat.title != null) {
      String name=chat.title.toLowerCase();
      state.bindString(2,name);
    }
 else {
      state.bindString(2,"");
    }
    state.bindByteBuffer(3,data);
    state.step();
    data.reuse();
  }
  state.dispose();
}
