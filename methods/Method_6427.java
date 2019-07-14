public int getCurrentDownloadMask(){
  if (ApplicationLoader.isConnectedToWiFi()) {
    if (!wifiPreset.enabled) {
      return 0;
    }
    int mask=0;
    for (int a=0; a < 4; a++) {
      mask|=getCurrentWiFiPreset().mask[a];
    }
    return mask;
  }
 else   if (ApplicationLoader.isRoaming()) {
    if (!roamingPreset.enabled) {
      return 0;
    }
    int mask=0;
    for (int a=0; a < 4; a++) {
      mask|=getCurrentRoamingPreset().mask[a];
    }
    return mask;
  }
 else {
    if (!mobilePreset.enabled) {
      return 0;
    }
    int mask=0;
    for (int a=0; a < 4; a++) {
      mask|=getCurrentMobilePreset().mask[a];
    }
    return mask;
  }
}
