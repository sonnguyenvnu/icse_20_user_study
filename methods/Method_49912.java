public static boolean allowAutoDownload(Context context){
  try {
    Looper.prepare();
  }
 catch (  Exception e) {
  }
  boolean autoDownload=PreferenceManager.getDefaultSharedPreferences(context).getBoolean("auto_download_mms",true);
  boolean dataSuspended=(((TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE)).getDataState() == TelephonyManager.DATA_SUSPENDED);
  return autoDownload && !dataSuspended;
}
