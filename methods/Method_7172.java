protected void putToSendingMessages(TLRPC.Message message){
  sendingMessages.put(message.id,message);
}
