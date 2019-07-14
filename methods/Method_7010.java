private void putUsersInternal(ArrayList<TLRPC.User> users) throws Exception {
  if (users == null || users.isEmpty()) {
    return;
  }
  SQLitePreparedStatement state=database.executeFast("REPLACE INTO users VALUES(?, ?, ?, ?)");
  for (int a=0; a < users.size(); a++) {
    TLRPC.User user=users.get(a);
    if (user.min) {
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data FROM users WHERE uid = %d",user.id));
      if (cursor.next()) {
        try {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLRPC.User oldUser=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
            data.reuse();
            if (oldUser != null) {
              if (user.username != null) {
                oldUser.username=user.username;
                oldUser.flags|=8;
              }
 else {
                oldUser.username=null;
                oldUser.flags=oldUser.flags & ~8;
              }
              if (user.photo != null) {
                oldUser.photo=user.photo;
                oldUser.flags|=32;
              }
 else {
                oldUser.photo=null;
                oldUser.flags=oldUser.flags & ~32;
              }
              user=oldUser;
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      cursor.dispose();
    }
    state.requery();
    NativeByteBuffer data=new NativeByteBuffer(user.getObjectSize());
    user.serializeToStream(data);
    state.bindInteger(1,user.id);
    state.bindString(2,formatUserSearchName(user));
    if (user.status != null) {
      if (user.status instanceof TLRPC.TL_userStatusRecently) {
        user.status.expires=-100;
      }
 else       if (user.status instanceof TLRPC.TL_userStatusLastWeek) {
        user.status.expires=-101;
      }
 else       if (user.status instanceof TLRPC.TL_userStatusLastMonth) {
        user.status.expires=-102;
      }
      state.bindInteger(3,user.status.expires);
    }
 else {
      state.bindInteger(3,0);
    }
    state.bindByteBuffer(4,data);
    state.step();
    data.reuse();
  }
  state.dispose();
}
