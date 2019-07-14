private Drawable getScamDrawable(){
  if (scamDrawable == null) {
    scamDrawable=new ScamDrawable(11);
    scamDrawable.setColor(AvatarDrawable.getProfileTextColorForId(user_id != 0 || ChatObject.isChannel(chat_id,currentAccount) && !currentChat.megagroup ? 5 : chat_id));
  }
  return scamDrawable;
}
