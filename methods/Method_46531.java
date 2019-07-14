@Override public void onAfterGetConnection(ConnectionInformation connectionInformation,SQLException e){
  DefaultEventListener.INSTANCE.onAfterGetConnection(connectionInformation,e);
  p6spyEventListener.onAfterGetConnection(connectionInformation,e);
}
