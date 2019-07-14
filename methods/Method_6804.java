public String getForwardedName(){
  if (messageOwner.fwd_from != null) {
    if (messageOwner.fwd_from.channel_id != 0) {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.fwd_from.channel_id);
      if (chat != null) {
        return chat.title;
      }
    }
 else     if (messageOwner.fwd_from.from_id != 0) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(messageOwner.fwd_from.from_id);
      if (user != null) {
        return UserObject.getUserName(user);
      }
    }
 else     if (messageOwner.fwd_from.from_name != null) {
      return messageOwner.fwd_from.from_name;
    }
  }
  return null;
}
