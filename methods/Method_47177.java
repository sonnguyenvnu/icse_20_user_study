public void saveColorPreferences(SharedPreferences prefs,UserColorPreferences userPrefs){
  SharedPreferences.Editor editor=prefs.edit();
  editor.putInt(PreferencesConstants.PREFERENCE_SKIN,userPrefs.primaryFirstTab);
  editor.putInt(PreferencesConstants.PREFERENCE_SKIN_TWO,userPrefs.primarySecondTab);
  editor.putInt(PreferencesConstants.PREFERENCE_ACCENT,userPrefs.accent);
  editor.putInt(PreferencesConstants.PREFERENCE_ICON_SKIN,userPrefs.iconSkin);
  editor.apply();
  currentColors=userPrefs;
}
