static boolean getAutoDownloadState(Context context,SharedPreferences prefs){
  return getAutoDownloadState(prefs,isRoaming(context));
}
