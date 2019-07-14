@Override public void refreshTheme(ThemeHelper t){
  rv.setBackgroundColor(t.getBackgroundColor());
  adapter.refreshTheme(t);
  refresh.setColorSchemeColors(t.getAccentColor());
  refresh.setProgressBackgroundColorSchemeColor(t.getBackgroundColor());
}
