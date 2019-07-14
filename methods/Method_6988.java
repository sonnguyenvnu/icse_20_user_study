public void updateChatInfo(final TLRPC.ChatFull info,final boolean ifExist){
  storageQueue.postRunnable(() -> {
    try {
      int currentOnline=-1;
      SQLiteCursor cursor=database.queryFinalized("SELECT online FROM chat_settings_v2 WHERE uid = " + info.id);
      if (cursor.next()) {
        currentOnline=cursor.intValue(0);
      }
      cursor.dispose();
      if (ifExist && currentOnline == -1) {
        return;
      }
      if (currentOnline >= 0 && (info.flags & 8192) == 0) {
        info.online_count=currentOnline;
      }
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?, ?)");
      NativeByteBuffer data=new NativeByteBuffer(info.getObjectSize());
      info.serializeToStream(data);
      state.bindInteger(1,info.id);
      state.bindByteBuffer(2,data);
      state.bindInteger(3,info.pinned_msg_id);
      state.bindInteger(4,info.online_count);
      state.step();
      state.dispose();
      data.reuse();
      if (info instanceof TLRPC.TL_channelFull) {
        cursor=database.queryFinalized("SELECT inbox_max, outbox_max FROM dialogs WHERE did = " + (-info.id));
        if (cursor.next()) {
          int inbox_max=cursor.intValue(0);
          if (inbox_max < info.read_inbox_max_id) {
            int outbox_max=cursor.intValue(1);
            state=database.executeFast("UPDATE dialogs SET unread_count = ?, inbox_max = ?, outbox_max = ? WHERE did = ?");
            state.bindInteger(1,info.unread_count);
            state.bindInteger(2,info.read_inbox_max_id);
            state.bindInteger(3,Math.max(outbox_max,info.read_outbox_max_id));
            state.bindLong(4,-info.id);
            state.step();
            state.dispose();
          }
        }
        cursor.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
