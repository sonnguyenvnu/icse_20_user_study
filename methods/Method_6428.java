public void savePresetToServer(int type){
  TLRPC.TL_account_saveAutoDownloadSettings req=new TLRPC.TL_account_saveAutoDownloadSettings();
  Preset preset;
  boolean enabled;
  if (type == 0) {
    preset=getCurrentMobilePreset();
    enabled=mobilePreset.enabled;
  }
 else   if (type == 1) {
    preset=getCurrentWiFiPreset();
    enabled=wifiPreset.enabled;
  }
 else {
    preset=getCurrentRoamingPreset();
    enabled=roamingPreset.enabled;
  }
  req.settings=new TLRPC.TL_autoDownloadSettings();
  req.settings.audio_preload_next=preset.preloadMusic;
  req.settings.video_preload_large=preset.preloadVideo;
  req.settings.phonecalls_less_data=preset.lessCallData;
  req.settings.disabled=!enabled;
  boolean photo=false;
  boolean video=false;
  boolean document=false;
  for (int a=0; a < preset.mask.length; a++) {
    if ((preset.mask[a] & AUTODOWNLOAD_TYPE_PHOTO) != 0) {
      photo=true;
    }
    if ((preset.mask[a] & AUTODOWNLOAD_TYPE_VIDEO) != 0) {
      video=true;
    }
    if ((preset.mask[a] & AUTODOWNLOAD_TYPE_DOCUMENT) != 0) {
      document=true;
    }
    if (photo && video && document) {
      break;
    }
  }
  req.settings.photo_size_max=photo ? preset.sizes[PRESET_SIZE_NUM_PHOTO] : 0;
  req.settings.video_size_max=video ? preset.sizes[PRESET_SIZE_NUM_VIDEO] : 0;
  req.settings.file_size_max=document ? preset.sizes[PRESET_SIZE_NUM_DOCUMENT] : 0;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
}
