public void setBotCommand(String command,String help,TLRPC.User user){
  if (user != null) {
    imageView.setVisibility(VISIBLE);
    avatarDrawable.setInfo(user);
    if (user.photo != null && user.photo.photo_small != null) {
      imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    }
 else {
      imageView.setImageDrawable(avatarDrawable);
    }
  }
 else {
    imageView.setVisibility(INVISIBLE);
  }
  usernameTextView.setVisibility(VISIBLE);
  nameTextView.setText(command);
  usernameTextView.setText(Emoji.replaceEmoji(help,usernameTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false));
}
