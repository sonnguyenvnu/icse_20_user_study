public static void logDebug(){
  Log.w(TAG,"BuildConfig.DEBUG:--" + BuildConfig.DEBUG + "--");
  if (BuildConfig.DEBUG)   Logger.w("is debug mode");
 else   Logger.w("not debug mode");
}
