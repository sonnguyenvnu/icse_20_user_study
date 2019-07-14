public void updateUserInfo(final TLRPC.UserFull info,final boolean ifExist){
  storageQueue.postRunnable(() -> {
    try {
      if (ifExist) {
        SQLiteCursor cursor=database.queryFinalized("SELECT uid FROM user_settings WHERE uid = " + info.user.id);
        boolean exist=cursor.next();
        cursor.dispose();
        if (!exist) {
          return;
        }
      }
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO user_settings VALUES(?, ?, ?)");
      NativeByteBuffer data=new NativeByteBuffer(info.getObjectSize());
      info.serializeToStream(data);
      state.bindInteger(1,info.user.id);
      state.bindByteBuffer(2,data);
      state.bindInteger(3,info.pinned_msg_id);
      state.step();
      state.dispose();
      data.reuse();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
