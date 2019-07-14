public void putPushMessage(MessageObject message){
  storageQueue.postRunnable(() -> {
    try {
      NativeByteBuffer data=new NativeByteBuffer(message.messageOwner.getObjectSize());
      message.messageOwner.serializeToStream(data);
      long messageId=message.getId();
      if (message.messageOwner.to_id.channel_id != 0) {
        messageId|=((long)message.messageOwner.to_id.channel_id) << 32;
      }
      int flags=0;
      if (message.localType == 2) {
        flags|=1;
      }
      if (message.localChannel) {
        flags|=2;
      }
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO unread_push_messages VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
      state.requery();
      state.bindLong(1,message.getDialogId());
      state.bindLong(2,messageId);
      state.bindLong(3,message.messageOwner.random_id);
      state.bindInteger(4,message.messageOwner.date);
      state.bindByteBuffer(5,data);
      if (message.messageText == null) {
        state.bindNull(6);
      }
 else {
        state.bindString(6,message.messageText.toString());
      }
      if (message.localName == null) {
        state.bindNull(7);
      }
 else {
        state.bindString(7,message.localName);
      }
      if (message.localUserName == null) {
        state.bindNull(8);
      }
 else {
        state.bindString(8,message.localUserName);
      }
      state.bindInteger(9,flags);
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
