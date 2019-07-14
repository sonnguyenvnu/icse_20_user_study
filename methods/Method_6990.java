public void updateChatPinnedMessage(final int channelId,final int messageId){
  storageQueue.postRunnable(() -> {
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT info, pinned, online FROM chat_settings_v2 WHERE uid = " + channelId);
      TLRPC.ChatFull info=null;
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          info=TLRPC.ChatFull.TLdeserialize(data,data.readInt32(false),false);
          data.reuse();
          info.pinned_msg_id=cursor.intValue(1);
          info.online_count=cursor.intValue(2);
        }
      }
      cursor.dispose();
      if (info != null) {
        if (info instanceof TLRPC.TL_channelFull) {
          info.pinned_msg_id=messageId;
          info.flags|=32;
        }
 else         if (info instanceof TLRPC.TL_chatFull) {
          info.pinned_msg_id=messageId;
          info.flags|=64;
        }
        final TLRPC.ChatFull finalInfo=info;
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,finalInfo,0,false,null));
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?, ?)");
        NativeByteBuffer data=new NativeByteBuffer(info.getObjectSize());
        info.serializeToStream(data);
        state.bindInteger(1,channelId);
        state.bindByteBuffer(2,data);
        state.bindInteger(3,info.pinned_msg_id);
        state.bindInteger(4,info.online_count);
        state.step();
        state.dispose();
        data.reuse();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
