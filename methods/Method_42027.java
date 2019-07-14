@CallSuper public void updateUiElements(){
  super.updateUiElements();
  toolbar.setBackgroundColor(themeOnSingleImgAct() ? ColorPalette.getTransparentColor(getPrimaryColor(),255 - Hawk.get(getString(R.string.preference_transparency),0)) : ColorPalette.getTransparentColor(getDefaultThemeToolbarColor3th(),175));
  toolbar.setPopupTheme(getPopupToolbarStyle());
  activityBackground.setBackgroundColor(getBackgroundColor());
  setStatusBarColor();
  setNavBarColor();
  setRecentApp(getString(R.string.app_name));
  ((TextView)findViewById(R.id.emoji_easter_egg)).setTextColor(getSubTextColor());
  ((TextView)findViewById(R.id.nothing_to_show_text_emoji_easter_egg)).setTextColor(getSubTextColor());
  if (Prefs.getToggleValue(getString(R.string.preference_max_brightness),false))   updateBrightness(1.0F);
 else   try {
    float brightness=android.provider.Settings.System.getInt(getContentResolver(),android.provider.Settings.System.SCREEN_BRIGHTNESS);
    brightness=brightness == 1.0F ? 255.0F : brightness;
    updateBrightness(brightness);
  }
 catch (  Settings.SettingNotFoundException e) {
    e.printStackTrace();
  }
  if (Prefs.getToggleValue(getString(R.string.preference_auto_rotate),false))   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
 else   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
}
