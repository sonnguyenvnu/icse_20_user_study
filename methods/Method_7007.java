public void getEncryptedChat(final int chat_id,final CountDownLatch countDownLatch,final ArrayList<TLObject> result){
  if (countDownLatch == null || result == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      ArrayList<TLRPC.EncryptedChat> encryptedChats=new ArrayList<>();
      getEncryptedChatsInternal("" + chat_id,encryptedChats,usersToLoad);
      if (!encryptedChats.isEmpty() && !usersToLoad.isEmpty()) {
        ArrayList<TLRPC.User> users=new ArrayList<>();
        getUsersInternal(TextUtils.join(",",usersToLoad),users);
        if (!users.isEmpty()) {
          result.add(encryptedChats.get(0));
          result.add(users.get(0));
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      countDownLatch.countDown();
    }
  }
);
}
