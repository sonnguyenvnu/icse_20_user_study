protected RemotingServer initRemotingServer(){
  RemotingServer remotingServer=new RpcServer(serverConfig.getBoundHost(),serverConfig.getPort());
  remotingServer.registerUserProcessor(boltServerProcessor);
  return remotingServer;
}
