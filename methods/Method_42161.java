public int columnsCount(){
  return DeviceUtils.isPortrait(getResources()) ? Prefs.getMediaColumnsPortrait() : Prefs.getMediaColumnsLandscape();
}
