@Override public void onDestroy(){
  unregisterReceiver(connectivityReceiver);
  syncManager.stopListening();
}
