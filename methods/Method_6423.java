protected int getAutodownloadMaskAll(){
  if (!mobilePreset.enabled && !roamingPreset.enabled && !wifiPreset.enabled) {
    return 0;
  }
  int mask=0;
  for (int a=0; a < 4; a++) {
    if ((getCurrentMobilePreset().mask[a] & AUTODOWNLOAD_TYPE_PHOTO) != 0 || (getCurrentWiFiPreset().mask[a] & AUTODOWNLOAD_TYPE_PHOTO) != 0 || (getCurrentRoamingPreset().mask[a] & AUTODOWNLOAD_TYPE_PHOTO) != 0) {
      mask|=AUTODOWNLOAD_TYPE_PHOTO;
    }
    if ((getCurrentMobilePreset().mask[a] & AUTODOWNLOAD_TYPE_AUDIO) != 0 || (getCurrentWiFiPreset().mask[a] & AUTODOWNLOAD_TYPE_AUDIO) != 0 || (getCurrentRoamingPreset().mask[a] & AUTODOWNLOAD_TYPE_AUDIO) != 0) {
      mask|=AUTODOWNLOAD_TYPE_AUDIO;
    }
    if ((getCurrentMobilePreset().mask[a] & AUTODOWNLOAD_TYPE_VIDEO) != 0 || (getCurrentWiFiPreset().mask[a] & AUTODOWNLOAD_TYPE_VIDEO) != 0 || (getCurrentRoamingPreset().mask[a] & AUTODOWNLOAD_TYPE_VIDEO) != 0) {
      mask|=AUTODOWNLOAD_TYPE_VIDEO;
    }
    if ((getCurrentMobilePreset().mask[a] & AUTODOWNLOAD_TYPE_DOCUMENT) != 0 || (getCurrentWiFiPreset().mask[a] & AUTODOWNLOAD_TYPE_DOCUMENT) != 0 || (getCurrentRoamingPreset().mask[a] & AUTODOWNLOAD_TYPE_DOCUMENT) != 0) {
      mask|=AUTODOWNLOAD_TYPE_DOCUMENT;
    }
  }
  return mask;
}
