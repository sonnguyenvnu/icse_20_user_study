public T brow(String socketAddress) throws ThriftConnectException {
  try {
    return connectionPool.borrowObject(socketAddress,1000);
  }
 catch (  PoolExhaustedException e) {
    logger.error("thrift client ?????");
    e.printStackTrace();
    throw new ThriftConnectException(e);
  }
catch (  NoValidObjectException e) {
    logger.error("thrift Connection??????");
    e.printStackTrace();
    throw new ThriftConnectException(e);
  }
catch (  InterruptedException e) {
    e.printStackTrace();
  }
  return null;
}
