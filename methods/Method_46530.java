@Override public void onBeforeGetConnection(ConnectionInformation connectionInformation){
  DefaultEventListener.INSTANCE.onBeforeGetConnection(connectionInformation);
  p6spyEventListener.onBeforeGetConnection(connectionInformation);
}
