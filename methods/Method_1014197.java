@Override public boolean connect(){
  if (connection != -1) {
    return false;
  }
  if (bgHandler.bgConnect(address,addressType)) {
    connectionState=ConnectionState.CONNECTING;
    return true;
  }
 else {
    connectionState=ConnectionState.DISCONNECTED;
    return false;
  }
}
