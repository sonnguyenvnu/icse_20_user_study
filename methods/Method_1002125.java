private static void logd(String msg){
  if (GcmPrefs.get(null).isGcmLogEnabled())   Log.d(TAG,msg);
}
