@Override protected void onDialogClosed(boolean positiveResult){
  if (positiveResult) {
    sharedPrefs.edit().putInt(PreferencesConstants.PREFERENCE_COLOR_CONFIG,selectedIndex).apply();
    if (selectedIndex != CUSTOM_INDEX && selectedIndex != RANDOM_INDEX) {
      colorPreferenceHelper.saveColorPreferences(sharedPrefs,new UserColorPreferences(getColor(selectedIndex,0),getColor(selectedIndex,1),getColor(selectedIndex,2),getColor(selectedIndex,3)));
    }
    listener.onAcceptedConfig();
  }
 else {
    selectedIndex=sharedPrefs.getInt(PreferencesConstants.PREFERENCE_COLOR_CONFIG,NO_DATA);
  }
}
