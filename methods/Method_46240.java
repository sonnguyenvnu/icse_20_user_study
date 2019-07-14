@Override public void disconnect(){
  try {
    connectionManager.closeConnection(RPC_CLIENT,transportConfig,url);
  }
 catch (  Exception e) {
    throw new SofaRpcRuntimeException("",e);
  }
}
