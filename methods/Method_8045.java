public void setDialog(int uid,boolean counter,CharSequence name){
  dialog_id=uid;
  if (uid > 0) {
    currentUser=MessagesController.getInstance(currentAccount).getUser(uid);
    if (name != null) {
      nameTextView.setText(name);
    }
 else     if (currentUser != null) {
      nameTextView.setText(UserObject.getFirstName(currentUser));
    }
 else {
      nameTextView.setText("");
    }
    avatarDrawable.setInfo(currentUser);
    imageView.setImage(ImageLocation.getForUser(currentUser,false),"50_50",avatarDrawable,currentUser);
  }
 else {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-uid);
    if (name != null) {
      nameTextView.setText(name);
    }
 else     if (chat != null) {
      nameTextView.setText(chat.title);
    }
 else {
      nameTextView.setText("");
    }
    avatarDrawable.setInfo(chat);
    currentUser=null;
    imageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
  }
  if (counter) {
    update(0);
  }
 else {
    countLayout=null;
  }
}
