@Override protected void applyPreferences(){
  super.applyPreferences();
  if (jmode != null) {
    jmode.loadPreferences();
    Messages.log("Applying prefs");
    pdex.preferencesChanged();
  }
}
