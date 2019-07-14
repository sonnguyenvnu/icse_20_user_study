private void checkCustomization(){
  boolean enableCustomization=sharedPref.getInt(PreferencesConstants.PREFERENCE_COLOR_CONFIG,ColorPickerDialog.NO_DATA) == ColorPickerDialog.CUSTOM_INDEX;
  findPreference(PreferencesConstants.PREFERENCE_SKIN).setEnabled(enableCustomization);
  findPreference(PreferencesConstants.PREFERENCE_SKIN_TWO).setEnabled(enableCustomization);
  findPreference(PreferencesConstants.PREFERENCE_ACCENT).setEnabled(enableCustomization);
  findPreference(PreferencesConstants.PREFERENCE_ICON_SKIN).setEnabled(enableCustomization);
}
