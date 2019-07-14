public void setDialog(int uid,boolean checked,CharSequence name){
  if (uid > 0) {
    user=MessagesController.getInstance(currentAccount).getUser(uid);
    avatarDrawable.setInfo(user);
    if (UserObject.isUserSelf(user)) {
      nameTextView.setText(LocaleController.getString("SavedMessages",R.string.SavedMessages));
      avatarDrawable.setAvatarType(AvatarDrawable.AVATAR_TYPE_SAVED);
      imageView.setImage(null,null,avatarDrawable,user);
    }
 else {
      if (name != null) {
        nameTextView.setText(name);
      }
 else       if (user != null) {
        nameTextView.setText(ContactsController.formatName(user.first_name,user.last_name));
      }
 else {
        nameTextView.setText("");
      }
      imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    }
  }
 else {
    user=null;
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
    imageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
  }
  checkBox.setChecked(checked,false);
}
