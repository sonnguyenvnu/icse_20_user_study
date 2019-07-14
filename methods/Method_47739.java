private void updateSync(){
  if (prefs == null)   return;
  boolean enabled=prefs.isSyncEnabled();
  Preference syncKey=findPreference("pref_sync_key");
  if (syncKey != null) {
    syncKey.setSummary(prefs.getSyncKey());
    syncKey.setVisible(enabled);
  }
  Preference syncAddress=findPreference("pref_sync_address");
  if (syncAddress != null) {
    syncAddress.setSummary(prefs.getSyncAddress());
    syncAddress.setVisible(enabled);
  }
}
