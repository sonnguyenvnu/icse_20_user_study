@Override public int clusterSize(){
  return rpcClient.loadAllRemoteKey().size();
}
