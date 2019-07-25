public void write(Context context){
  context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE).edit().putLong(PREF_ANDROID_ID,androidId).putString(PREF_DIGEST,digest).putLong(PREF_LAST_CHECKIN,lastCheckin).putLong(PREF_SECURITY_TOKEN,securityToken).putString(PREF_VERSION_INFO,versionInfo).putString(PREF_DEVICE_DATA_VERSION_INFO,deviceDataVersionInfo).apply();
}
