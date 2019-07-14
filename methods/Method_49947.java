static boolean getAutoDownloadState(SharedPreferences prefs,boolean roaming){
  boolean autoDownload=prefs.getBoolean("auto_download_mms",true);
  if (LOCAL_LOGV) {
    Timber.v("auto download without roaming -> " + autoDownload);
  }
  if (autoDownload) {
    boolean alwaysAuto=true;
    if (LOCAL_LOGV) {
      Timber.v("auto download during roaming -> " + alwaysAuto);
    }
    if (!roaming || alwaysAuto) {
      return true;
    }
  }
  return false;
}
