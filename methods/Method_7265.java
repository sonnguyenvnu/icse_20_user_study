@Override public void onCustomTabsServiceConnected(ComponentName name,CustomTabsClient client){
  ServiceConnectionCallback connectionCallback=mConnectionCallback.get();
  if (connectionCallback != null)   connectionCallback.onServiceConnected(client);
}
