public Connection fetchConnection(){
  return connectionManager.getConnection(RPC_CLIENT,transportConfig,url);
}
