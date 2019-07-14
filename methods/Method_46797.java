void initialisePreferences(){
  currentTab=getPrefs().getInt(PreferencesConstants.PREFERENCE_CURRENT_TAB,PreferenceUtils.DEFAULT_CURRENT_TAB);
  @ColorInt int currentPrimary=ColorPreferenceHelper.getPrimary(getCurrentColorPreference(),MainActivity.currentTab);
  skinStatusBar=PreferenceUtils.getStatusColor(currentPrimary);
}
