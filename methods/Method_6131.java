public static void checkForUpdates(Activity context){
  if (BuildVars.DEBUG_VERSION) {
    UpdateManager.register(context,BuildVars.DEBUG_VERSION ? BuildVars.HOCKEY_APP_HASH_DEBUG : BuildVars.HOCKEY_APP_HASH);
  }
}
