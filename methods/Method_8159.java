public void setIsAdmin(int value){
  if (adminTextView == null) {
    return;
  }
  adminTextView.setVisibility(value != 0 ? VISIBLE : GONE);
  if (value == 1) {
    adminTextView.setText(LocaleController.getString("ChannelCreator",R.string.ChannelCreator));
  }
 else   if (value == 2) {
    adminTextView.setText(LocaleController.getString("ChannelAdmin",R.string.ChannelAdmin));
  }
  if (value != 0) {
    CharSequence text=adminTextView.getText();
    int size=(int)Math.ceil(adminTextView.getPaint().measureText(text,0,text.length()));
    nameTextView.setPadding(LocaleController.isRTL ? size + AndroidUtilities.dp(6) : 0,0,!LocaleController.isRTL ? size + AndroidUtilities.dp(6) : 0,0);
  }
 else {
    nameTextView.setPadding(0,0,0,0);
  }
}
