private UserColorPreferences getColorPreferences(Context c,SharedPreferences prefs){
  if (isUsingOldColorsSystem(prefs))   correctToNewColorsSystem(c,prefs);
  int tabOne=prefs.getInt(PreferencesConstants.PREFERENCE_SKIN,Utils.getColor(c,DEFAULT_PRIMARY_FIRST_TAB));
  int tabTwo=prefs.getInt(PreferencesConstants.PREFERENCE_SKIN_TWO,Utils.getColor(c,DEFAULT_PRIMARY_SECOND_TAB));
  int accent=prefs.getInt(PreferencesConstants.PREFERENCE_ACCENT,Utils.getColor(c,DEFAULT_ACCENT));
  int iconSkin=prefs.getInt(PreferencesConstants.PREFERENCE_ICON_SKIN,Utils.getColor(c,DEFAULT_ICON_SKIN));
  return new UserColorPreferences(tabOne,tabTwo,accent,iconSkin);
}
