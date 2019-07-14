private boolean showDescriptionCard(){
  return !isExcludedMode() && Prefs.getToggleValue(getString(R.string.preference_show_tips),true);
}
