@Override public Connection proxyConnection(ConnectionCallback connectionCallback) throws Throwable {
  String groupId=DTXLocalContext.cur().getGroupId();
  try {
    return globalContext.getLcnConnection(groupId);
  }
 catch (  TCGlobalContextException e) {
    LcnConnectionProxy lcnConnectionProxy=new LcnConnectionProxy(connectionCallback.call());
    globalContext.setLcnConnection(groupId,lcnConnectionProxy);
    lcnConnectionProxy.setAutoCommit(false);
    return lcnConnectionProxy;
  }
}
