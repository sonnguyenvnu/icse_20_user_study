public void setIsDarkTheme(boolean isDarkTheme){
  if (isDarkTheme) {
    nameTextView.setTextColor(0xffffffff);
    usernameTextView.setTextColor(0xffbbbbbb);
  }
 else {
    nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
    usernameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
  }
}
