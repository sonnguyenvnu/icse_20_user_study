public static boolean isNightMode(@NonNull Resources resources){
  @PrefGetter.ThemeType int themeType=PrefGetter.getThemeType(resources);
  return themeType != PrefGetter.LIGHT;
}
