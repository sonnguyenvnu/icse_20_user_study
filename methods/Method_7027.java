private void markMessagesAsReadInternal(SparseLongArray inbox,SparseLongArray outbox,SparseIntArray encryptedMessages){
  try {
    if (!isEmpty(inbox)) {
      SQLitePreparedStatement state=database.executeFast("DELETE FROM unread_push_messages WHERE uid = ? AND mid <= ?");
      for (int b=0; b < inbox.size(); b++) {
        int key=inbox.keyAt(b);
        long messageId=inbox.get(key);
        database.executeFast(String.format(Locale.US,"UPDATE messages SET read_state = read_state | 1 WHERE uid = %d AND mid > 0 AND mid <= %d AND read_state IN(0,2) AND out = 0",key,messageId)).stepThis().dispose();
        state.requery();
        state.bindLong(1,key);
        state.bindLong(2,messageId);
        state.step();
      }
      state.dispose();
    }
    if (!isEmpty(outbox)) {
      for (int b=0; b < outbox.size(); b++) {
        int key=outbox.keyAt(b);
        long messageId=outbox.get(key);
        database.executeFast(String.format(Locale.US,"UPDATE messages SET read_state = read_state | 1 WHERE uid = %d AND mid > 0 AND mid <= %d AND read_state IN(0,2) AND out = 1",key,messageId)).stepThis().dispose();
      }
    }
    if (encryptedMessages != null && !isEmpty(encryptedMessages)) {
      for (int a=0; a < encryptedMessages.size(); a++) {
        long dialog_id=((long)encryptedMessages.keyAt(a)) << 32;
        int max_date=encryptedMessages.valueAt(a);
        SQLitePreparedStatement state=database.executeFast("UPDATE messages SET read_state = read_state | 1 WHERE uid = ? AND date <= ? AND read_state IN(0,2) AND out = 1");
        state.requery();
        state.bindLong(1,dialog_id);
        state.bindInteger(2,max_date);
        state.step();
        state.dispose();
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
