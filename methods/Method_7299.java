public int getNewMessageId(){
  int id;
synchronized (sync) {
    id=lastSendMessageId;
    lastSendMessageId--;
  }
  return id;
}
