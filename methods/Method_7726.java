public static long getSelectedBackgroundId(){
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  int background=preferences.getInt("selectedBackground",(int)DEFAULT_BACKGROUND_ID);
  if (background != DEFAULT_BACKGROUND_ID) {
    preferences.edit().putLong("selectedBackground2",background).remove("selectedBackground").commit();
  }
  long id=preferences.getLong("selectedBackground2",DEFAULT_BACKGROUND_ID);
  if (hasWallpaperFromTheme() && !preferences.getBoolean("overrideThemeWallpaper",false)) {
    return THEME_BACKGROUND_ID;
  }
 else   if (id == THEME_BACKGROUND_ID) {
    return DEFAULT_BACKGROUND_ID;
  }
  return id;
}
