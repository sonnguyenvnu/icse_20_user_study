public void setCheckBackground(boolean withRound,boolean animated){
  if (checkDrawable == null) {
    checkDrawable=new CheckDrawable();
    checkBackgroundDrawable=Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48),checkDrawable,0);
  }
  Theme.setCombinedDrawableColor(checkBackgroundDrawable,Theme.getColor(Theme.key_chat_mediaLoaderPhoto),false);
  Theme.setCombinedDrawableColor(checkBackgroundDrawable,Theme.getColor(Theme.key_chat_mediaLoaderPhotoIcon),true);
  if (currentDrawable != checkBackgroundDrawable) {
    setBackground(checkBackgroundDrawable,withRound,animated);
    checkDrawable.resetProgress(animated);
  }
}
