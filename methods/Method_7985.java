private boolean isOpenChatByShare(MessageObject messageObject){
  return messageObject.messageOwner.fwd_from != null && messageObject.messageOwner.fwd_from.saved_from_peer != null;
}
