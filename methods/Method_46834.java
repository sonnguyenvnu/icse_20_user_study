public @ColorInt int getAccent(){
  return getColorPreference().getCurrentUserColorPreferences(this,getPrefs()).accent;
}
