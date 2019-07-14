@Override public InetSocketAddress localAddress(){
  Connection connection=fetchConnection();
  return connection == null ? null : connection.getRemoteAddress();
}
