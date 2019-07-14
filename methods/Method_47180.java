private void correctToNewColorsSystem(Context c,SharedPreferences prefs){
  int tabOne=prefs.getInt(PreferencesConstants.PREFERENCE_SKIN,-1);
  int tabTwo=prefs.getInt(PreferencesConstants.PREFERENCE_SKIN_TWO,-1);
  int accent=prefs.getInt(PreferencesConstants.PREFERENCE_ACCENT,-1);
  int iconSkin=prefs.getInt(PreferencesConstants.PREFERENCE_ICON_SKIN,-1);
  SharedPreferences.Editor editor=prefs.edit();
  editor.putInt(PreferencesConstants.PREFERENCE_SKIN,correctForIndex(c,tabOne));
  editor.putInt(PreferencesConstants.PREFERENCE_SKIN_TWO,correctForIndex(c,tabTwo));
  editor.putInt(PreferencesConstants.PREFERENCE_ACCENT,correctForIndex(c,accent));
  editor.putInt(PreferencesConstants.PREFERENCE_ICON_SKIN,correctForIndex(c,iconSkin));
  editor.apply();
}
