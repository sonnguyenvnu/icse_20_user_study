public void putBotKeyboard(final long did,final TLRPC.Message message){
  if (message == null) {
    return;
  }
  try {
    int mid=0;
    SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT mid FROM bot_keyboard WHERE uid = %d",did));
    if (cursor.next()) {
      mid=cursor.intValue(0);
    }
    cursor.dispose();
    if (mid >= message.id) {
      return;
    }
    SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO bot_keyboard VALUES(?, ?, ?)");
    state.requery();
    NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
    message.serializeToStream(data);
    state.bindLong(1,did);
    state.bindInteger(2,message.id);
    state.bindByteBuffer(3,data);
    state.step();
    data.reuse();
    state.dispose();
    AndroidUtilities.runOnUIThread(() -> {
      TLRPC.Message old=botKeyboards.get(did);
      botKeyboards.put(did,message);
      if (old != null) {
        botKeyboardsByMids.delete(old.id);
      }
      botKeyboardsByMids.put(message.id,did);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.botKeyboardDidLoad,message,did);
    }
);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
