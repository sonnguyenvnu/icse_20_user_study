public void loadAutoDownloadConfig(boolean force){
  if (loadingAutoDownloadConfig || !force && Math.abs(System.currentTimeMillis() - UserConfig.getInstance(currentAccount).autoDownloadConfigLoadTime) < 24 * 60 * 60 * 1000) {
    return;
  }
  loadingAutoDownloadConfig=true;
  TLRPC.TL_account_getAutoDownloadSettings req=new TLRPC.TL_account_getAutoDownloadSettings();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    loadingAutoDownloadConfig=false;
    UserConfig.getInstance(currentAccount).autoDownloadConfigLoadTime=System.currentTimeMillis();
    UserConfig.getInstance(currentAccount).saveConfig(false);
    if (response != null) {
      TLRPC.TL_account_autoDownloadSettings res=(TLRPC.TL_account_autoDownloadSettings)response;
      lowPreset.set(res.low);
      mediumPreset.set(res.medium);
      highPreset.set(res.high);
      for (int a=0; a < 3; a++) {
        Preset preset;
        if (a == 0) {
          preset=mobilePreset;
        }
 else         if (a == 1) {
          preset=wifiPreset;
        }
 else {
          preset=roamingPreset;
        }
        if (preset.equals(lowPreset)) {
          preset.set(res.low);
        }
 else         if (preset.equals(mediumPreset)) {
          preset.set(res.medium);
        }
 else         if (preset.equals(highPreset)) {
          preset.set(res.high);
        }
      }
      SharedPreferences.Editor editor=MessagesController.getMainSettings(currentAccount).edit();
      editor.putString("mobilePreset",mobilePreset.toString());
      editor.putString("wifiPreset",wifiPreset.toString());
      editor.putString("roamingPreset",roamingPreset.toString());
      editor.putString("preset0",lowPreset.toString());
      editor.putString("preset1",mediumPreset.toString());
      editor.putString("preset2",highPreset.toString());
      editor.commit();
      String str1=lowPreset.toString();
      String str2=mediumPreset.toString();
      String str3=highPreset.toString();
      checkAutodownloadSettings();
    }
  }
));
}
