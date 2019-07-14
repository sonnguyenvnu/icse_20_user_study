public void setEmoji(String e,int side){
  emoji=e;
  imageView.setImageDrawable(Emoji.getEmojiBigDrawable(e));
  if (side == -1) {
    setBackgroundResource(R.drawable.stickers_back_left);
    setPadding(AndroidUtilities.dp(7),0,0,0);
  }
 else   if (side == 0) {
    setBackgroundResource(R.drawable.stickers_back_center);
    setPadding(0,0,0,0);
  }
 else   if (side == 1) {
    setBackgroundResource(R.drawable.stickers_back_right);
    setPadding(0,0,AndroidUtilities.dp(7),0);
  }
 else   if (side == 2) {
    setBackgroundResource(R.drawable.stickers_back_all);
    setPadding(AndroidUtilities.dp(3),0,AndroidUtilities.dp(3),0);
  }
  Drawable background=getBackground();
  if (background != null) {
    background.setAlpha(230);
    background.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_stickersHintPanel),PorterDuff.Mode.MULTIPLY));
  }
}
