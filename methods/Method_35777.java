@Override public void updateGlobalSettings(GlobalSettings newSettings){
  GlobalSettings oldSettings=globalSettingsHolder.get();
  for (  GlobalSettingsListener listener : globalSettingsListeners) {
    listener.beforeGlobalSettingsUpdated(oldSettings,newSettings);
  }
  globalSettingsHolder.replaceWith(newSettings);
  for (  GlobalSettingsListener listener : globalSettingsListeners) {
    listener.afterGlobalSettingsUpdated(oldSettings,newSettings);
  }
}
