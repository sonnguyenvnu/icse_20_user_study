@Override public Connection proxyConnection(ConnectionCallback connectionCallback) throws Throwable {
  return connectionCallback.call();
}
