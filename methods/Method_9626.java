private void updateUserData(){
  TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(UserConfig.getInstance(currentAccount).getClientUserId());
  if (user == null) {
    return;
  }
  TLRPC.FileLocation photoBig=null;
  if (user.photo != null) {
    photoBig=user.photo.photo_big;
  }
  avatarDrawable=new AvatarDrawable(user,true);
  avatarDrawable.setColor(Theme.getColor(Theme.key_avatar_backgroundInProfileBlue));
  if (avatarImage != null) {
    avatarImage.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    avatarImage.getImageReceiver().setVisible(!PhotoViewer.isShowingImage(photoBig),false);
    nameTextView.setText(UserObject.getUserName(user));
    onlineTextView.setText(LocaleController.getString("Online",R.string.Online));
    avatarImage.getImageReceiver().setVisible(!PhotoViewer.isShowingImage(photoBig),false);
  }
}
