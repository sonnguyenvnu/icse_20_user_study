public void update(){
  heartbeatMs=Integer.parseInt(defaultPreferences.getString(PREF_HEARTBEAT,"300")) * 1000;
  gcmLogEnabled=defaultPreferences.getBoolean(PREF_FULL_LOG,true);
  lastPersistedId=defaultPreferences.getString(PREF_LAST_PERSISTENT_ID,"");
  confirmNewApps=defaultPreferences.getBoolean(PREF_CONFIRM_NEW_APPS,false);
  gcmEnabled=defaultPreferences.getBoolean(PREF_ENABLE_GCM,false);
  networkMobile=Integer.parseInt(defaultPreferences.getString(PREF_NETWORK_MOBILE,"0"));
  networkWifi=Integer.parseInt(defaultPreferences.getString(PREF_NETWORK_WIFI,"0"));
  networkRoaming=Integer.parseInt(defaultPreferences.getString(PREF_NETWORK_ROAMING,"0"));
  networkOther=Integer.parseInt(defaultPreferences.getString(PREF_NETWORK_OTHER,"0"));
  learntMobile=defaultPreferences.getInt(PREF_LEARNT_MOBILE,300000);
  learntWifi=defaultPreferences.getInt(PREF_LEARNT_WIFI,300000);
  learntOther=defaultPreferences.getInt(PREF_LEARNT_OTHER,300000);
}
