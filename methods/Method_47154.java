private void invalidateEverything(){
  activity.invalidateRecentsColorAndIcon();
  activity.invalidateToolbarColor();
  activity.invalidateNavBar();
  if (currentSection == SECTION_1) {
    ColorPickerDialog selectedColors=(ColorPickerDialog)findPreference(KEY_PRESELECTED_CONFIGS);
    if (selectedColors != null) {
      invalidateColorPreference(selectedColors);
      selectedColors.invalidateColors();
    }
    ((InvalidablePreferenceCategory)findPreference("category")).invalidate(activity.getAccent());
  }
}
