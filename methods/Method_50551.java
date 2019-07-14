@Override public Server choose(final Object key){
  List<Server> serverList=getLoadBalancer().getAllServers();
  if (null == serverList || serverList.isEmpty() || serverList.size() == 1) {
    return super.choose(key);
  }
  final Server server=super.choose(key);
  final HmilyTransactionContext hmilyTransactionContext=HmilyTransactionContextLocal.getInstance().get();
  if (Objects.isNull(hmilyTransactionContext)) {
    return server;
  }
  final String transId=hmilyTransactionContext.getTransId();
  if (hmilyTransactionContext.getAction() == HmilyActionEnum.TRYING.getCode()) {
    SERVER_MAP.put(transId,server);
    return server;
  }
  final Server oldServer=SERVER_MAP.get(transId);
  SERVER_MAP.remove(transId);
  if (Objects.nonNull(oldServer)) {
    for (    Server s : serverList) {
      if (Objects.equals(s,oldServer)) {
        return oldServer;
      }
    }
  }
  return server;
}
