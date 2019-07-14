public void updateEncryptedChatSeq(final TLRPC.EncryptedChat chat,final boolean cleanup){
  if (chat == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    SQLitePreparedStatement state=null;
    try {
      state=database.executeFast("UPDATE enc_chats SET seq_in = ?, seq_out = ?, use_count = ?, in_seq_no = ?, mtproto_seq = ? WHERE uid = ?");
      state.bindInteger(1,chat.seq_in);
      state.bindInteger(2,chat.seq_out);
      state.bindInteger(3,(int)chat.key_use_count_in << 16 | chat.key_use_count_out);
      state.bindInteger(4,chat.in_seq_no);
      state.bindInteger(5,chat.mtproto_seq);
      state.bindInteger(6,chat.id);
      state.step();
      if (cleanup) {
        long did=((long)chat.id) << 32;
        database.executeFast(String.format(Locale.US,"DELETE FROM messages WHERE mid IN (SELECT m.mid FROM messages as m LEFT JOIN messages_seq as s ON m.mid = s.mid WHERE m.uid = %d AND m.date = 0 AND m.mid < 0 AND s.seq_out <= %d)",did,chat.in_seq_no)).stepThis().dispose();
      }
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
