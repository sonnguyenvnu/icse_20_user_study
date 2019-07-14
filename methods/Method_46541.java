@Override public Connection proxyConnection(ConnectionCallback connectionCallback) throws Throwable {
  return connectionHelper.proxy(connectionCallback.call());
}
