public void setDialog(LocationController.SharingLocationInfo info){
  currentInfo=info;
  int lower_id=(int)info.did;
  if (lower_id > 0) {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(lower_id);
    if (user != null) {
      avatarDrawable.setInfo(user);
      nameTextView.setText(ContactsController.formatName(user.first_name,user.last_name));
      avatarImageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    }
  }
 else {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
    if (chat != null) {
      avatarDrawable.setInfo(chat);
      nameTextView.setText(chat.title);
      avatarImageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
    }
  }
}
