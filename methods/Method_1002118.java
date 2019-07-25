public static LastCheckinInfo read(Context context){
  LastCheckinInfo info=new LastCheckinInfo();
  SharedPreferences preferences=context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
  info.androidId=preferences.getLong(PREF_ANDROID_ID,0);
  info.digest=preferences.getString(PREF_DIGEST,INITIAL_DIGEST);
  info.lastCheckin=preferences.getLong(PREF_LAST_CHECKIN,0);
  info.securityToken=preferences.getLong(PREF_SECURITY_TOKEN,0);
  info.versionInfo=preferences.getString(PREF_VERSION_INFO,"");
  info.deviceDataVersionInfo=preferences.getString(PREF_DEVICE_DATA_VERSION_INFO,"");
  return info;
}
