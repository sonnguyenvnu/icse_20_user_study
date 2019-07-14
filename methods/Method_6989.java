public void updateUserPinnedMessage(final int userId,final int messageId){
  storageQueue.postRunnable(() -> {
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT info, pinned FROM user_settings WHERE uid = " + userId);
      TLRPC.UserFull info=null;
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          info=TLRPC.UserFull.TLdeserialize(data,data.readInt32(false),false);
          data.reuse();
          info.pinned_msg_id=cursor.intValue(1);
        }
      }
      cursor.dispose();
      if (info instanceof TLRPC.UserFull) {
        info.pinned_msg_id=messageId;
        info.flags|=64;
        final TLRPC.UserFull finalInfo=info;
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad,userId,finalInfo,null));
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO user_settings VALUES(?, ?, ?)");
        NativeByteBuffer data=new NativeByteBuffer(info.getObjectSize());
        info.serializeToStream(data);
        state.bindInteger(1,userId);
        state.bindByteBuffer(2,data);
        state.bindInteger(3,info.pinned_msg_id);
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
