@Override public boolean stopService(Intent name){
  stopForeground(true);
  unregisterCallback(this);
  return super.stopService(name);
}
