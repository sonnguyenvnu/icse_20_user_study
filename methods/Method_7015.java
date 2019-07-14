public void getEncryptedChatsInternal(String chatsToLoad,ArrayList<TLRPC.EncryptedChat> result,ArrayList<Integer> usersToLoad) throws Exception {
  if (chatsToLoad == null || chatsToLoad.length() == 0 || result == null) {
    return;
  }
  SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data, user, g, authkey, ttl, layer, seq_in, seq_out, use_count, exchange_id, key_date, fprint, fauthkey, khash, in_seq_no, admin_id, mtproto_seq FROM enc_chats WHERE uid IN(%s)",chatsToLoad));
  while (cursor.next()) {
    try {
      NativeByteBuffer data=cursor.byteBufferValue(0);
      if (data != null) {
        TLRPC.EncryptedChat chat=TLRPC.EncryptedChat.TLdeserialize(data,data.readInt32(false),false);
        data.reuse();
        if (chat != null) {
          chat.user_id=cursor.intValue(1);
          if (usersToLoad != null && !usersToLoad.contains(chat.user_id)) {
            usersToLoad.add(chat.user_id);
          }
          chat.a_or_b=cursor.byteArrayValue(2);
          chat.auth_key=cursor.byteArrayValue(3);
          chat.ttl=cursor.intValue(4);
          chat.layer=cursor.intValue(5);
          chat.seq_in=cursor.intValue(6);
          chat.seq_out=cursor.intValue(7);
          int use_count=cursor.intValue(8);
          chat.key_use_count_in=(short)(use_count >> 16);
          chat.key_use_count_out=(short)(use_count);
          chat.exchange_id=cursor.longValue(9);
          chat.key_create_date=cursor.intValue(10);
          chat.future_key_fingerprint=cursor.longValue(11);
          chat.future_auth_key=cursor.byteArrayValue(12);
          chat.key_hash=cursor.byteArrayValue(13);
          chat.in_seq_no=cursor.intValue(14);
          int admin_id=cursor.intValue(15);
          if (admin_id != 0) {
            chat.admin_id=admin_id;
          }
          chat.mtproto_seq=cursor.intValue(16);
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
