public void update(){
  disabled=defaultPreferences.getBoolean(PREF_SNET_DISABLED,true);
  official=defaultPreferences.getBoolean(PREF_SNET_OFFICIAL,false);
  selfSigned=defaultPreferences.getBoolean(PREF_SNET_SELF_SIGNED,false);
  thirdParty=defaultPreferences.getBoolean(PREF_SNET_THIRD_PARTY,false);
  customUrl=defaultPreferences.getString(PREF_SNET_CUSTOM_URL,null);
}
