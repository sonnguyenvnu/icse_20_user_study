/** 
 * Determins whether or not the app has enabled MMS over WiFi
 * @param context
 * @return true if enabled
 */
public static boolean isMmsOverWifiEnabled(Context context){
  return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mms_over_wifi",false);
}
