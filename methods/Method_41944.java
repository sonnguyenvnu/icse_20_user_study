@Override public void refreshTheme(ThemeHelper themeHelper){
  super.refreshTheme(themeHelper);
  int borderColor=themeHelper.getInvertedBackgroundColor();
  profileImage.setBorderColor(borderColor);
}
