protected void checkSettings(){
  if (!settingsInited)   defaultSettings();
  if (reapplySettings)   reapplySettings();
}
