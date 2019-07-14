public void setSyncAddress(String address){
  storage.putString("pref_sync_address",address);
  for (  Listener l : listeners)   l.onSyncFeatureChanged();
}
