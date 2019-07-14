public void loadChatInfo(final int chat_id,CountDownLatch countDownLatch,boolean force){
  MessagesStorage.getInstance(currentAccount).loadChatInfo(chat_id,countDownLatch,force,false);
}
