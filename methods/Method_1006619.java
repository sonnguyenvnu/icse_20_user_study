/** 
 * @see com.alipay.remoting.ConnectionManager#check(com.alipay.remoting.Connection)
 */
@Override public void check(Connection connection) throws RemotingException {
  if (connection == null) {
    throw new RemotingException("Connection is null when do check!");
  }
  if (connection.getChannel() == null || !connection.getChannel().isActive()) {
    this.remove(connection);
    throw new RemotingException("Check connection failed for address: " + connection.getUrl());
  }
  if (!connection.getChannel().isWritable()) {
    throw new RemotingException("Check connection failed for address: " + connection.getUrl() + ", maybe write overflow!");
  }
}
