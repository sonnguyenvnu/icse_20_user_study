/** 
 * @see com.alipay.remoting.ConnectionManager#create(com.alipay.remoting.Url)
 */
@Override public Connection create(Url url) throws RemotingException {
  Connection conn;
  try {
    conn=this.connectionFactory.createConnection(url);
  }
 catch (  Exception e) {
    throw new RemotingException("Create connection failed. The address is " + url.getOriginUrl(),e);
  }
  return conn;
}
