@Override public void onSyncFeatureChanged(){
  if (!prefs.isSyncEnabled())   stopSelf();
}
