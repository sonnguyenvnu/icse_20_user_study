public TLRPC.Chat getChatSync(final int chat_id){
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  final TLRPC.Chat[] chat=new TLRPC.Chat[1];
  storageQueue.postRunnable(() -> {
    chat[0]=getChat(chat_id);
    countDownLatch.countDown();
  }
);
  try {
    countDownLatch.await();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return chat[0];
}
