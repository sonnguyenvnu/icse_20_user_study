@Override public void refreshTheme(ThemeHelper theme){
  placeholder=theme.getPlaceHolder();
  cardViewStyle=Prefs.getCardStyle();
  super.refreshTheme(theme);
}
