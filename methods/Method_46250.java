@Override public InetSocketAddress remoteAddress(){
  Connection connection=fetchConnection();
  return connection == null ? null : connection.getRemoteAddress();
}
