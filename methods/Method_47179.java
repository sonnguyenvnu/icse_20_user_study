/** 
 * The old system used indexes, from here on in this file a correction is made so that the indexes are converted into ColorInts
 */
private boolean isUsingOldColorsSystem(SharedPreferences prefs){
  int tabOne=prefs.getInt(PreferencesConstants.PREFERENCE_SKIN,R.color.primary_indigo);
  int tabTwo=prefs.getInt(PreferencesConstants.PREFERENCE_SKIN_TWO,R.color.primary_indigo);
  int accent=prefs.getInt(PreferencesConstants.PREFERENCE_ACCENT,R.color.primary_pink);
  int iconSkin=prefs.getInt(PreferencesConstants.PREFERENCE_ICON_SKIN,R.color.primary_pink);
  boolean r1=tabOne >= 0 && tabTwo >= 0 && accent >= 0 && iconSkin >= 0;
  boolean r2=tabOne < 22 && tabTwo < 22 && accent < 22 && iconSkin < 22;
  return r1 && r2;
}
