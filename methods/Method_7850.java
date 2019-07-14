private int getSelectedColor(){
  int currentColor=selectedColor;
  if (nightModeEnabled && currentColor != 2) {
    if (Theme.selectedAutoNightType != Theme.AUTO_NIGHT_TYPE_NONE) {
      if (Theme.isCurrentThemeNight()) {
        currentColor=2;
      }
    }
 else {
      int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
      if (hour >= 22 && hour <= 24 || hour >= 0 && hour <= 6) {
        currentColor=2;
      }
    }
  }
  return currentColor;
}
