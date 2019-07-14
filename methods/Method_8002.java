public void setUser(TLRPC.User user,boolean checked,boolean divider){
  currentUser=user;
  textView.setText(ContactsController.formatName(user.first_name,user.last_name));
  checkBox.setChecked(checked,false);
  avatarDrawable.setInfo(user);
  imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
  needDivider=divider;
  setWillNotDraw(!divider);
}
