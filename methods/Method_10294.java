protected void sendMessage(Message msg){
  if (getUseSynchronousMode() || handler == null) {
    handleMessage(msg);
  }
 else   if (!Thread.currentThread().isInterrupted()) {
    Utils.asserts(handler != null,"handler should not be null!");
    handler.sendMessage(msg);
  }
}
