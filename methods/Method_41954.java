private boolean showNothingToShowPlaceholder(){
  return folders.size() < 1 && isExcludedMode() && !Prefs.showEasterEgg();
}
