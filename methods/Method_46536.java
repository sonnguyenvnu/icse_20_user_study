@Override public void onAfterConnectionClose(ConnectionInformation connectionInformation,SQLException e){
  DefaultEventListener.INSTANCE.onAfterConnectionClose(connectionInformation,e);
  p6spyEventListener.onAfterConnectionClose(connectionInformation,e);
}
