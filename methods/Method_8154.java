public void updateCurrentThemeCheck(){
  Theme.ThemeInfo currentTheme;
  if (isNightTheme) {
    currentTheme=Theme.getCurrentNightTheme();
  }
 else {
    currentTheme=Theme.getCurrentTheme();
  }
  int newVisibility=currentThemeInfo == currentTheme ? VISIBLE : INVISIBLE;
  if (checkImage.getVisibility() != newVisibility) {
    checkImage.setVisibility(newVisibility);
  }
}
