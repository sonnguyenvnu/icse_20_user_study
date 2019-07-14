private void setPlayDrawable(boolean play){
  Drawable circle=Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(46),Theme.getColor(Theme.key_musicPicker_buttonBackground),Theme.getColor(Theme.key_musicPicker_buttonBackground));
  Drawable drawable=getResources().getDrawable(play ? R.drawable.audiosend_pause : R.drawable.audiosend_play);
  drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_musicPicker_buttonIcon),PorterDuff.Mode.MULTIPLY));
  CombinedDrawable combinedDrawable=new CombinedDrawable(circle,drawable);
  combinedDrawable.setCustomSize(AndroidUtilities.dp(46),AndroidUtilities.dp(46));
  playButton.setBackgroundDrawable(combinedDrawable);
  playButton.setContentDescription(play ? LocaleController.getString("AccActionPause",R.string.AccActionPause) : LocaleController.getString("AccActionPlay",R.string.AccActionPlay));
}
