protected boolean canDownloadNextTrack(){
  if (ApplicationLoader.isConnectedToWiFi()) {
    return wifiPreset.enabled && getCurrentWiFiPreset().preloadMusic;
  }
 else   if (ApplicationLoader.isRoaming()) {
    return roamingPreset.enabled && getCurrentRoamingPreset().preloadMusic;
  }
 else {
    return mobilePreset.enabled && getCurrentMobilePreset().preloadMusic;
  }
}
