public void setUser(TLRPC.User user){
  nameTextView.setText(ContactsController.formatName(user.first_name,user.last_name));
  avatarDrawable.setInfo(user);
  imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
}
