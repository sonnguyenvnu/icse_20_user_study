@Override public boolean isConnectionFine(RpcClient rpcClient,ClientTransportConfig transportConfig,Url url){
  Connection connection;
  try {
    connection=rpcClient.getConnection(url,url.getConnectTimeout());
  }
 catch (  RemotingException e) {
    return false;
  }
catch (  InterruptedException e) {
    return false;
  }
  return connection != null && connection.isFine();
}
