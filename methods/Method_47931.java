public void setSyncKey(String key){
  storage.putString("pref_sync_key",key);
  for (  Listener l : listeners)   l.onSyncFeatureChanged();
}
