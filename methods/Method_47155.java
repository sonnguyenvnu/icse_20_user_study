private void invalidateColorPreference(ColorPickerDialog selectedColors){
  int colorPickerPref=sharedPref.getInt(PreferencesConstants.PREFERENCE_COLOR_CONFIG,ColorPickerDialog.NO_DATA);
  boolean isColor=colorPickerPref != ColorPickerDialog.CUSTOM_INDEX && colorPickerPref != ColorPickerDialog.RANDOM_INDEX;
  if (isColor) {
    selectedColors.setColorsVisibility(View.VISIBLE);
    UserColorPreferences userColorPreferences=activity.getCurrentColorPreference();
    selectedColors.setColors(userColorPreferences.primaryFirstTab,userColorPreferences.primarySecondTab,userColorPreferences.accent,userColorPreferences.iconSkin);
    if (activity.getAppTheme().getMaterialDialogTheme() == Theme.LIGHT) {
      selectedColors.setDividerColor(Color.WHITE);
    }
 else {
      selectedColors.setDividerColor(Color.BLACK);
    }
  }
 else {
    selectedColors.setColorsVisibility(View.GONE);
  }
}
