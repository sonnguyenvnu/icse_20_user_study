protected TLRPC.Message removeFromSendingMessages(int mid){
  TLRPC.Message message=sendingMessages.get(mid);
  if (message != null) {
    sendingMessages.remove(mid);
  }
  return message;
}
