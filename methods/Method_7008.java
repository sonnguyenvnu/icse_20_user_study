public void putEncryptedChat(final TLRPC.EncryptedChat chat,final TLRPC.User user,final TLRPC.Dialog dialog){
  if (chat == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      if ((chat.key_hash == null || chat.key_hash.length < 16) && chat.auth_key != null) {
        chat.key_hash=AndroidUtilities.calcAuthKeyHash(chat.auth_key);
      }
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO enc_chats VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      NativeByteBuffer data=new NativeByteBuffer(chat.getObjectSize());
      NativeByteBuffer data2=new NativeByteBuffer(chat.a_or_b != null ? chat.a_or_b.length : 1);
      NativeByteBuffer data3=new NativeByteBuffer(chat.auth_key != null ? chat.auth_key.length : 1);
      NativeByteBuffer data4=new NativeByteBuffer(chat.future_auth_key != null ? chat.future_auth_key.length : 1);
      NativeByteBuffer data5=new NativeByteBuffer(chat.key_hash != null ? chat.key_hash.length : 1);
      chat.serializeToStream(data);
      state.bindInteger(1,chat.id);
      state.bindInteger(2,user.id);
      state.bindString(3,formatUserSearchName(user));
      state.bindByteBuffer(4,data);
      if (chat.a_or_b != null) {
        data2.writeBytes(chat.a_or_b);
      }
      if (chat.auth_key != null) {
        data3.writeBytes(chat.auth_key);
      }
      if (chat.future_auth_key != null) {
        data4.writeBytes(chat.future_auth_key);
      }
      if (chat.key_hash != null) {
        data5.writeBytes(chat.key_hash);
      }
      state.bindByteBuffer(5,data2);
      state.bindByteBuffer(6,data3);
      state.bindInteger(7,chat.ttl);
      state.bindInteger(8,chat.layer);
      state.bindInteger(9,chat.seq_in);
      state.bindInteger(10,chat.seq_out);
      state.bindInteger(11,(int)chat.key_use_count_in << 16 | chat.key_use_count_out);
      state.bindLong(12,chat.exchange_id);
      state.bindInteger(13,chat.key_create_date);
      state.bindLong(14,chat.future_key_fingerprint);
      state.bindByteBuffer(15,data4);
      state.bindByteBuffer(16,data5);
      state.bindInteger(17,chat.in_seq_no);
      state.bindInteger(18,chat.admin_id);
      state.bindInteger(19,chat.mtproto_seq);
      state.step();
      state.dispose();
      data.reuse();
      data2.reuse();
      data3.reuse();
      data4.reuse();
      data5.reuse();
      if (dialog != null) {
        state=database.executeFast("REPLACE INTO dialogs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        state.bindLong(1,dialog.id);
        state.bindInteger(2,dialog.last_message_date);
        state.bindInteger(3,dialog.unread_count);
        state.bindInteger(4,dialog.top_message);
        state.bindInteger(5,dialog.read_inbox_max_id);
        state.bindInteger(6,dialog.read_outbox_max_id);
        state.bindInteger(7,0);
        state.bindInteger(8,dialog.unread_mentions_count);
        state.bindInteger(9,dialog.pts);
        state.bindInteger(10,0);
        state.bindInteger(11,dialog.pinnedNum);
        state.bindInteger(12,dialog.flags);
        state.bindInteger(13,dialog.folder_id);
        state.bindNull(14);
        state.step();
        state.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
