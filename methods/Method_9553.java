private void checkAndUpdateAvatar(){
  if (currentMessageObject == null) {
    return;
  }
  if (currentChat != null) {
    TLRPC.Chat chat=MessagesController.getInstance(currentMessageObject.currentAccount).getChat(currentChat.id);
    if (chat == null) {
      return;
    }
    currentChat=chat;
    if (avatarImageView != null) {
      AvatarDrawable avatarDrawable=new AvatarDrawable(currentChat);
      avatarImageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
    }
  }
 else   if (currentUser != null) {
    TLRPC.User user=MessagesController.getInstance(currentMessageObject.currentAccount).getUser(currentUser.id);
    if (user == null) {
      return;
    }
    currentUser=user;
    if (avatarImageView != null) {
      AvatarDrawable avatarDrawable=new AvatarDrawable(currentUser);
      avatarImageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    }
  }
}
