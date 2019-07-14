@Keep public void setAnimationProgress(float progress){
  animationProgress=progress;
  listView.setAlpha(progress);
  listView.setTranslationX(AndroidUtilities.dp(48) - AndroidUtilities.dp(48) * progress);
  int color=AvatarDrawable.getProfileBackColorForId(user_id != 0 || ChatObject.isChannel(chat_id,currentAccount) && !currentChat.megagroup ? 5 : chat_id);
  int actionBarColor=Theme.getColor(Theme.key_actionBarDefault);
  int r=Color.red(actionBarColor);
  int g=Color.green(actionBarColor);
  int b=Color.blue(actionBarColor);
  int a;
  int rD=(int)((Color.red(color) - r) * progress);
  int gD=(int)((Color.green(color) - g) * progress);
  int bD=(int)((Color.blue(color) - b) * progress);
  int aD;
  topView.setBackgroundColor(Color.rgb(r + rD,g + gD,b + bD));
  color=AvatarDrawable.getIconColorForId(user_id != 0 || ChatObject.isChannel(chat_id,currentAccount) && !currentChat.megagroup ? 5 : chat_id);
  int iconColor=Theme.getColor(Theme.key_actionBarDefaultIcon);
  r=Color.red(iconColor);
  g=Color.green(iconColor);
  b=Color.blue(iconColor);
  rD=(int)((Color.red(color) - r) * progress);
  gD=(int)((Color.green(color) - g) * progress);
  bD=(int)((Color.blue(color) - b) * progress);
  actionBar.setItemsColor(Color.rgb(r + rD,g + gD,b + bD),false);
  color=Theme.getColor(Theme.key_profile_title);
  int titleColor=Theme.getColor(Theme.key_actionBarDefaultTitle);
  r=Color.red(titleColor);
  g=Color.green(titleColor);
  b=Color.blue(titleColor);
  a=Color.alpha(titleColor);
  rD=(int)((Color.red(color) - r) * progress);
  gD=(int)((Color.green(color) - g) * progress);
  bD=(int)((Color.blue(color) - b) * progress);
  aD=(int)((Color.alpha(color) - a) * progress);
  for (int i=0; i < 2; i++) {
    if (nameTextView[i] == null) {
      continue;
    }
    nameTextView[i].setTextColor(Color.argb(a + aD,r + rD,g + gD,b + bD));
  }
  color=isOnline[0] ? Theme.getColor(Theme.key_profile_status) : AvatarDrawable.getProfileTextColorForId(user_id != 0 || ChatObject.isChannel(chat_id,currentAccount) && !currentChat.megagroup ? 5 : chat_id);
  int subtitleColor=Theme.getColor(isOnline[0] ? Theme.key_chat_status : Theme.key_actionBarDefaultSubtitle);
  r=Color.red(subtitleColor);
  g=Color.green(subtitleColor);
  b=Color.blue(subtitleColor);
  a=Color.alpha(subtitleColor);
  rD=(int)((Color.red(color) - r) * progress);
  gD=(int)((Color.green(color) - g) * progress);
  bD=(int)((Color.blue(color) - b) * progress);
  aD=(int)((Color.alpha(color) - a) * progress);
  for (int i=0; i < 2; i++) {
    if (onlineTextView[i] == null) {
      continue;
    }
    onlineTextView[i].setTextColor(Color.argb(a + aD,r + rD,g + gD,b + bD));
  }
  extraHeight=(int)(initialAnimationExtraHeight * progress);
  color=AvatarDrawable.getProfileColorForId(user_id != 0 ? user_id : chat_id);
  int color2=AvatarDrawable.getColorForId(user_id != 0 ? user_id : chat_id);
  if (color != color2) {
    rD=(int)((Color.red(color) - Color.red(color2)) * progress);
    gD=(int)((Color.green(color) - Color.green(color2)) * progress);
    bD=(int)((Color.blue(color) - Color.blue(color2)) * progress);
    avatarDrawable.setColor(Color.rgb(Color.red(color2) + rD,Color.green(color2) + gD,Color.blue(color2) + bD));
    avatarImage.invalidate();
  }
  needLayout();
}
