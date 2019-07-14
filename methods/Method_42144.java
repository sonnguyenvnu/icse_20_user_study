public int columnsCount(){
  return DeviceUtils.isPortrait(getResources()) ? Prefs.getFolderColumnsPortrait() : Prefs.getFolderColumnsLandscape();
}
