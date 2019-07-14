@Override public void refreshTheme(ThemeHelper themeHelper){
  int textColor=themeHelper.getTextColor();
  int subTextColor=themeHelper.getSubTextColor();
  int iconColor=themeHelper.getIconColor();
  linkIcon.setColor(iconColor);
  linkTitle.setTextColor(textColor);
  linkDescription.setTextColor(subTextColor);
}
