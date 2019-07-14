private boolean showEasterEgg(){
  return folders.size() < 1 && isExcludedMode() && Prefs.showEasterEgg();
}
