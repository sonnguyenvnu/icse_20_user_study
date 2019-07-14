public void loadUserInfo(TLRPC.User user,final boolean force,int classGuid){
  if (user == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    MessageObject pinnedMessageObject=null;
    TLRPC.UserFull info=null;
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT info, pinned FROM user_settings WHERE uid = " + user.id);
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          info=TLRPC.UserFull.TLdeserialize(data,data.readInt32(false),false);
          data.reuse();
          info.pinned_msg_id=cursor.intValue(1);
        }
      }
      cursor.dispose();
      if (info != null && info.pinned_msg_id != 0) {
        pinnedMessageObject=DataQuery.getInstance(currentAccount).loadPinnedMessage(user.id,0,info.pinned_msg_id,false);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      MessagesController.getInstance(currentAccount).processUserInfo(user,info,true,force,pinnedMessageObject,classGuid);
    }
  }
);
}
