private boolean contains(UiMessage message){
  for (  UiMessage msg : messages) {
    if (message.message.messageId > 0) {
      if (msg.message.messageId == message.message.messageId) {
        return true;
      }
    }
 else     if (message.message.messageUid > 0) {
      if (msg.message.messageUid == message.message.messageUid) {
        return true;
      }
    }
  }
  return false;
}
