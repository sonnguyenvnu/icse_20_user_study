@Override public boolean isAvailable(){
  return connectionManager.isConnectionFine(RPC_CLIENT,transportConfig,url);
}
