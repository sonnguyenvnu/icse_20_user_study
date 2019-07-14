private void updateRepeatButton(){
  int mode=SharedConfig.repeatMode;
  if (mode == 0) {
    repeatButton.setImageResource(R.drawable.pl_repeat);
    repeatButton.setTag(Theme.key_player_button);
    repeatButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_player_button),PorterDuff.Mode.MULTIPLY));
    repeatButton.setContentDescription(LocaleController.getString("AccDescrRepeatOff",R.string.AccDescrRepeatOff));
  }
 else   if (mode == 1) {
    repeatButton.setImageResource(R.drawable.pl_repeat);
    repeatButton.setTag(Theme.key_player_buttonActive);
    repeatButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_player_buttonActive),PorterDuff.Mode.MULTIPLY));
    repeatButton.setContentDescription(LocaleController.getString("AccDescrRepeatList",R.string.AccDescrRepeatList));
  }
 else   if (mode == 2) {
    repeatButton.setImageResource(R.drawable.pl_repeat1);
    repeatButton.setTag(Theme.key_player_buttonActive);
    repeatButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_player_buttonActive),PorterDuff.Mode.MULTIPLY));
    repeatButton.setContentDescription(LocaleController.getString("AccDescrRepeatOne",R.string.AccDescrRepeatOne));
  }
}
