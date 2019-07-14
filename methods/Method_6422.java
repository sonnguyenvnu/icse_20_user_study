public int getAutodownloadMask(){
  int result=0;
  int[] masksArray;
  if (ApplicationLoader.isConnectedToWiFi()) {
    if (!wifiPreset.enabled) {
      return 0;
    }
    masksArray=getCurrentWiFiPreset().mask;
  }
 else   if (ApplicationLoader.isRoaming()) {
    if (!roamingPreset.enabled) {
      return 0;
    }
    masksArray=getCurrentRoamingPreset().mask;
  }
 else {
    if (!mobilePreset.enabled) {
      return 0;
    }
    masksArray=getCurrentMobilePreset().mask;
  }
  for (int a=0; a < masksArray.length; a++) {
    int mask=0;
    if ((masksArray[a] & AUTODOWNLOAD_TYPE_PHOTO) != 0) {
      mask|=AUTODOWNLOAD_TYPE_PHOTO;
    }
    if ((masksArray[a] & AUTODOWNLOAD_TYPE_AUDIO) != 0) {
      mask|=AUTODOWNLOAD_TYPE_AUDIO;
    }
    if ((masksArray[a] & AUTODOWNLOAD_TYPE_VIDEO) != 0) {
      mask|=AUTODOWNLOAD_TYPE_VIDEO;
    }
    if ((masksArray[a] & AUTODOWNLOAD_TYPE_DOCUMENT) != 0) {
      mask|=AUTODOWNLOAD_TYPE_DOCUMENT;
    }
    result|=mask << (a * 8);
  }
  return result;
}
