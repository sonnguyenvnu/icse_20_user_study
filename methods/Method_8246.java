private boolean sendSecretMessageRead(MessageObject messageObject){
  if (messageObject == null || messageObject.isOut() || !messageObject.isSecretMedia() || messageObject.messageOwner.destroyTime != 0 || messageObject.messageOwner.ttl <= 0) {
    return false;
  }
  if (currentEncryptedChat != null) {
    MessagesController.getInstance(currentAccount).markMessageAsRead(dialog_id,messageObject.messageOwner.random_id,messageObject.messageOwner.ttl);
  }
 else {
    MessagesController.getInstance(currentAccount).markMessageAsRead(messageObject.getId(),ChatObject.isChannel(currentChat) ? currentChat.id : 0,null,messageObject.messageOwner.ttl,0);
  }
  messageObject.messageOwner.destroyTime=messageObject.messageOwner.ttl + ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  return true;
}
