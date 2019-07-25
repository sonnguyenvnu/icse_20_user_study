@Override public List<HostPort> slaves(Sentinel sentinel,String sentinelMonitorName){
  SimpleObjectPool<NettyClient> clientPool=keyedClientPool.getKeyPool(new DefaultEndPoint(sentinel.getIp(),sentinel.getPort()));
  try {
    AbstractSentinelCommand.SentinelSlaves command=new AbstractSentinelCommand.SentinelSlaves(clientPool,scheduled,sentinelMonitorName);
    command.setCommandTimeoutMilli(LONG_SENTINEL_COMMAND_TIMEOUT);
    silentCommand(command);
    return command.execute().get();
  }
 catch (  Exception e) {
    logger.error("[slaves] sentinel: {}",sentinel,e);
  }
  return Lists.newArrayList();
}
