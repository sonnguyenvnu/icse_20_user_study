/** 
 * Load APN settings from system
 * @param context
 * @param apnName the optional APN name to match
 */
public static ApnSettings load(Context context,String apnName,int subId) throws ApnException {
  SharedPreferences sharedPrefs=PreferenceManager.getDefaultSharedPreferences(context);
  String mmsc=sharedPrefs.getString("mmsc_url","");
  if (!TextUtils.isEmpty(mmsc)) {
    String mmsProxy=sharedPrefs.getString("mms_proxy","");
    String mmsPort=sharedPrefs.getString("mms_port","");
    return new ApnSettings(mmsc,mmsProxy,parsePort(mmsPort),"Default from settings");
  }
  Timber.v("ApnSettings: apnName " + apnName);
  String selection=null;
  String[] selectionArgs=null;
  apnName=apnName != null ? apnName.trim() : null;
  if (!TextUtils.isEmpty(apnName)) {
    selection=Telephony.Carriers.APN + "=?";
    selectionArgs=new String[]{apnName};
  }
  Cursor cursor=null;
  try {
    cursor=SqliteWrapper.query(context,context.getContentResolver(),Uri.withAppendedPath(Telephony.Carriers.CONTENT_URI,"/subId/" + subId),APN_PROJECTION,selection,selectionArgs,null);
    if (cursor != null) {
      String mmscUrl=null;
      String proxyAddress=null;
      int proxyPort=-1;
      while (cursor.moveToNext()) {
        if (isValidApnType(cursor.getString(COLUMN_TYPE),"mms")) {
          mmscUrl=trimWithNullCheck(cursor.getString(COLUMN_MMSC));
          if (TextUtils.isEmpty(mmscUrl)) {
            continue;
          }
          mmscUrl=NetworkUtilsHelper.trimV4AddrZeros(mmscUrl);
          try {
            new URI(mmscUrl);
          }
 catch (          URISyntaxException e) {
            throw new ApnException("Invalid MMSC url " + mmscUrl);
          }
          proxyAddress=trimWithNullCheck(cursor.getString(COLUMN_MMSPROXY));
          if (!TextUtils.isEmpty(proxyAddress)) {
            proxyAddress=NetworkUtilsHelper.trimV4AddrZeros(proxyAddress);
            final String portString=trimWithNullCheck(cursor.getString(COLUMN_MMSPORT));
            if (portString != null) {
              try {
                proxyPort=Integer.parseInt(portString);
              }
 catch (              NumberFormatException e) {
                Timber.e("Invalid port " + portString);
                throw new ApnException("Invalid port " + portString);
              }
            }
          }
          return new ApnSettings(mmscUrl,proxyAddress,proxyPort,getDebugText(cursor));
        }
      }
    }
  }
  finally {
    if (cursor != null) {
      cursor.close();
    }
  }
  return new ApnSettings("","",80,"Failed to find APNs :(");
}
