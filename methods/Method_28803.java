@Override public boolean validateObject(PooledObject<Jedis> pooledJedis){
  final BinaryJedis jedis=pooledJedis.getObject();
  try {
    HostAndPort hostAndPort=this.hostAndPort.get();
    String connectionHost=jedis.getClient().getHost();
    int connectionPort=jedis.getClient().getPort();
    return hostAndPort.getHost().equals(connectionHost) && hostAndPort.getPort() == connectionPort && jedis.isConnected() && jedis.ping().equals("PONG");
  }
 catch (  final Exception e) {
    return false;
  }
}
