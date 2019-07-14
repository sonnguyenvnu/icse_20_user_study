public boolean canDownloadMedia(int type,int size){
  Preset preset;
  if (ApplicationLoader.isConnectedToWiFi()) {
    if (!wifiPreset.enabled) {
      return false;
    }
    preset=getCurrentWiFiPreset();
  }
 else   if (ApplicationLoader.isRoaming()) {
    if (!roamingPreset.enabled) {
      return false;
    }
    preset=getCurrentRoamingPreset();
  }
 else {
    if (!mobilePreset.enabled) {
      return false;
    }
    preset=getCurrentMobilePreset();
  }
  int mask=preset.mask[1];
  int maxSize=preset.sizes[typeToIndex(type)];
  return (type == AUTODOWNLOAD_TYPE_PHOTO || size != 0 && size <= maxSize) && (type == AUTODOWNLOAD_TYPE_AUDIO || (mask & type) != 0);
}
