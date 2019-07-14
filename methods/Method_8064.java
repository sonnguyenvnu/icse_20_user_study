public void setUser(TLRPC.User user){
  if (user == null) {
    nameTextView.setText("");
    usernameTextView.setText("");
    imageView.setImageDrawable(null);
    return;
  }
  avatarDrawable.setInfo(user);
  if (user.photo != null && user.photo.photo_small != null) {
    imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
  }
 else {
    imageView.setImageDrawable(avatarDrawable);
  }
  nameTextView.setText(UserObject.getUserName(user));
  if (user.username != null) {
    usernameTextView.setText("@" + user.username);
  }
 else {
    usernameTextView.setText("");
  }
  imageView.setVisibility(VISIBLE);
  usernameTextView.setVisibility(VISIBLE);
}
