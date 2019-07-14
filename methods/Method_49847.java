public static void init(Context context){
  if (LOCAL_LOGV) {
    Timber.v("MmsConfig.init()");
  }
  loadMmsSettings(context);
}
