protected void checkPreferencesChange(JComponent page){
  if (page instanceof PreferencesChangeListener) {
    Map<String,String> preferences=configuration.getPreferences();
    Integer currentHashcode=Integer.valueOf(preferences.hashCode());
    Integer lastHashcode=(Integer)page.getClientProperty("preferences-hashCode");
    if (!currentHashcode.equals(lastHashcode)) {
      ((PreferencesChangeListener)page).preferencesChanged(preferences);
      page.putClientProperty("preferences-hashCode",currentHashcode);
    }
  }
}
