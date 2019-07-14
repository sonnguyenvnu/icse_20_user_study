@NonNull private static String getCodeBackgroundColor(@NonNull Context context){
  @PrefGetter.ThemeType int themeType=PrefGetter.getThemeType();
  if (themeType == PrefGetter.BLUISH) {
    return "#" + Integer.toHexString(ViewHelper.getPrimaryDarkColor(context)).substring(2).toUpperCase();
  }
  return "#" + Integer.toHexString(ViewHelper.getPrimaryColor(context)).substring(2).toUpperCase();
}
