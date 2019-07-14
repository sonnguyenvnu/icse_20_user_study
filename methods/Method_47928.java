public String getSyncAddress(){
  return storage.getString("pref_sync_address",DEFAULT_SYNC_SERVER);
}
