@Override public boolean isConnectionFine(RpcClient rpcClient,ClientTransportConfig transportConfig,Url url){
  Connection connection=this.getConnection(rpcClient,transportConfig,url);
  return connection != null && connection.isFine();
}
