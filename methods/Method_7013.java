public void getUsersInternal(String usersToLoad,ArrayList<TLRPC.User> result) throws Exception {
  if (usersToLoad == null || usersToLoad.length() == 0 || result == null) {
    return;
  }
  SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data, status FROM users WHERE uid IN(%s)",usersToLoad));
  while (cursor.next()) {
    try {
      NativeByteBuffer data=cursor.byteBufferValue(0);
      if (data != null) {
        TLRPC.User user=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
        data.reuse();
        if (user != null) {
          if (user.status != null) {
            user.status.expires=cursor.intValue(1);
          }
          result.add(user);
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  cursor.dispose();
}
