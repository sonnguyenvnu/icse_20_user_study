public void getBlockedUsers(){
  storageQueue.postRunnable(() -> {
    try {
      SparseIntArray ids=new SparseIntArray();
      ArrayList<TLRPC.User> users=new ArrayList<>();
      SQLiteCursor cursor=database.queryFinalized("SELECT * FROM blocked_users WHERE 1");
      StringBuilder usersToLoad=new StringBuilder();
      while (cursor.next()) {
        int user_id=cursor.intValue(0);
        ids.put(user_id,1);
        if (usersToLoad.length() != 0) {
          usersToLoad.append(",");
        }
        usersToLoad.append(user_id);
      }
      cursor.dispose();
      if (usersToLoad.length() != 0) {
        getUsersInternal(usersToLoad.toString(),users);
      }
      MessagesController.getInstance(currentAccount).processLoadedBlockedUsers(ids,users,true);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
