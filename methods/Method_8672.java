private void updateStyle(int style){
  if (currentStyle == style) {
    return;
  }
  currentStyle=style;
  if (style == 0 || style == 2) {
    frameLayout.setBackgroundColor(Theme.getColor(Theme.key_inappPlayerBackground));
    frameLayout.setTag(Theme.key_inappPlayerBackground);
    titleTextView.setTextColor(Theme.getColor(Theme.key_inappPlayerTitle));
    titleTextView.setTag(Theme.key_inappPlayerTitle);
    closeButton.setVisibility(VISIBLE);
    playButton.setVisibility(VISIBLE);
    titleTextView.setTypeface(Typeface.DEFAULT);
    titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
    if (style == 0) {
      playButton.setLayoutParams(LayoutHelper.createFrame(36,36,Gravity.TOP | Gravity.LEFT,0,0,0,0));
      titleTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,36,Gravity.LEFT | Gravity.TOP,35,0,36,0));
      if (playbackSpeedButton != null) {
        playbackSpeedButton.setVisibility(VISIBLE);
      }
      closeButton.setContentDescription(LocaleController.getString("AccDescrClosePlayer",R.string.AccDescrClosePlayer));
    }
 else     if (style == 2) {
      playButton.setLayoutParams(LayoutHelper.createFrame(36,36,Gravity.TOP | Gravity.LEFT,8,0,0,0));
      titleTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,36,Gravity.LEFT | Gravity.TOP,35 + 16,0,36,0));
      closeButton.setContentDescription(LocaleController.getString("AccDescrStopLiveLocation",R.string.AccDescrStopLiveLocation));
    }
  }
 else   if (style == 1) {
    titleTextView.setText(LocaleController.getString("ReturnToCall",R.string.ReturnToCall));
    frameLayout.setBackgroundColor(Theme.getColor(Theme.key_returnToCallBackground));
    frameLayout.setTag(Theme.key_returnToCallBackground);
    titleTextView.setTextColor(Theme.getColor(Theme.key_returnToCallText));
    titleTextView.setTag(Theme.key_returnToCallText);
    closeButton.setVisibility(GONE);
    playButton.setVisibility(GONE);
    titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    titleTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER,0,0,0,2));
    titleTextView.setPadding(0,0,0,0);
    if (playbackSpeedButton != null) {
      playbackSpeedButton.setVisibility(GONE);
    }
  }
}
