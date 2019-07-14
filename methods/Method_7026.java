private void updateUsersInternal(final ArrayList<TLRPC.User> users,final boolean onlyStatus,final boolean withTransaction){
  if (Thread.currentThread().getId() != storageQueue.getId()) {
    throw new RuntimeException("wrong db thread");
  }
  try {
    if (onlyStatus) {
      if (withTransaction) {
        database.beginTransaction();
      }
      SQLitePreparedStatement state=database.executeFast("UPDATE users SET status = ? WHERE uid = ?");
      for (int a=0, N=users.size(); a < N; a++) {
        TLRPC.User user=users.get(a);
        state.requery();
        if (user.status != null) {
          state.bindInteger(1,user.status.expires);
        }
 else {
          state.bindInteger(1,0);
        }
        state.bindInteger(2,user.id);
        state.step();
      }
      state.dispose();
      if (withTransaction) {
        database.commitTransaction();
      }
    }
 else {
      StringBuilder ids=new StringBuilder();
      SparseArray<TLRPC.User> usersDict=new SparseArray<>();
      for (int a=0, N=users.size(); a < N; a++) {
        TLRPC.User user=users.get(a);
        if (ids.length() != 0) {
          ids.append(",");
        }
        ids.append(user.id);
        usersDict.put(user.id,user);
      }
      ArrayList<TLRPC.User> loadedUsers=new ArrayList<>();
      getUsersInternal(ids.toString(),loadedUsers);
      for (int a=0, N=loadedUsers.size(); a < N; a++) {
        TLRPC.User user=loadedUsers.get(a);
        TLRPC.User updateUser=usersDict.get(user.id);
        if (updateUser != null) {
          if (updateUser.first_name != null && updateUser.last_name != null) {
            if (!UserObject.isContact(user)) {
              user.first_name=updateUser.first_name;
              user.last_name=updateUser.last_name;
            }
            user.username=updateUser.username;
          }
 else           if (updateUser.photo != null) {
            user.photo=updateUser.photo;
          }
 else           if (updateUser.phone != null) {
            user.phone=updateUser.phone;
          }
        }
      }
      if (!loadedUsers.isEmpty()) {
        if (withTransaction) {
          database.beginTransaction();
        }
        putUsersInternal(loadedUsers);
        if (withTransaction) {
          database.commitTransaction();
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
