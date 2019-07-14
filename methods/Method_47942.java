public boolean shouldMakeNotificationsSticky(){
  return storage.getBoolean("pref_sticky_notifications",false);
}
