private void updateCurrentUserAndChat(){
  MessagesController messagesController=MessagesController.getInstance(currentAccount);
  TLRPC.MessageFwdHeader fwd_from=currentMessageObject.messageOwner.fwd_from;
  int currentUserId=UserConfig.getInstance(currentAccount).getClientUserId();
  if (fwd_from != null && fwd_from.channel_id != 0 && currentMessageObject.getDialogId() == currentUserId) {
    currentChat=MessagesController.getInstance(currentAccount).getChat(fwd_from.channel_id);
  }
 else   if (fwd_from != null && fwd_from.saved_from_peer != null) {
    if (fwd_from.saved_from_peer.user_id != 0) {
      if (fwd_from.from_id != 0) {
        currentUser=messagesController.getUser(fwd_from.from_id);
      }
 else {
        currentUser=messagesController.getUser(fwd_from.saved_from_peer.user_id);
      }
    }
 else     if (fwd_from.saved_from_peer.channel_id != 0) {
      if (currentMessageObject.isSavedFromMegagroup() && fwd_from.from_id != 0) {
        currentUser=messagesController.getUser(fwd_from.from_id);
      }
 else {
        currentChat=messagesController.getChat(fwd_from.saved_from_peer.channel_id);
      }
    }
 else     if (fwd_from.saved_from_peer.chat_id != 0) {
      if (fwd_from.from_id != 0) {
        currentUser=messagesController.getUser(fwd_from.from_id);
      }
 else {
        currentChat=messagesController.getChat(fwd_from.saved_from_peer.chat_id);
      }
    }
  }
 else   if (fwd_from != null && fwd_from.from_id != 0 && fwd_from.channel_id == 0 && currentMessageObject.getDialogId() == currentUserId) {
    currentUser=messagesController.getUser(fwd_from.from_id);
  }
 else   if (fwd_from != null && !TextUtils.isEmpty(fwd_from.from_name) && currentMessageObject.getDialogId() == currentUserId) {
    currentUser=new TLRPC.TL_user();
    currentUser.first_name=fwd_from.from_name;
  }
 else   if (currentMessageObject.isFromUser()) {
    currentUser=messagesController.getUser(currentMessageObject.messageOwner.from_id);
  }
 else   if (currentMessageObject.messageOwner.from_id < 0) {
    currentChat=messagesController.getChat(-currentMessageObject.messageOwner.from_id);
  }
 else   if (currentMessageObject.messageOwner.post) {
    currentChat=messagesController.getChat(currentMessageObject.messageOwner.to_id.channel_id);
  }
}
