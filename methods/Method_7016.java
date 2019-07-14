public void putUsersAndChats(final ArrayList<TLRPC.User> users,final ArrayList<TLRPC.Chat> chats,final boolean withTransaction,boolean useQueue){
  if (users != null && users.isEmpty() && chats != null && chats.isEmpty()) {
    return;
  }
  if (useQueue) {
    storageQueue.postRunnable(() -> putUsersAndChatsInternal(users,chats,withTransaction));
  }
 else {
    putUsersAndChatsInternal(users,chats,withTransaction);
  }
}
