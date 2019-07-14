private void initializeSlotsCache(Set<HostAndPort> startNodes,GenericObjectPoolConfig poolConfig){
  for (  HostAndPort hostAndPort : startNodes) {
    Jedis jedis=new Jedis(hostAndPort.getHost(),hostAndPort.getPort());
    try {
      cache.discoverClusterNodesAndSlots(jedis);
      break;
    }
 catch (    JedisConnectionException e) {
    }
 finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }
  for (  HostAndPort node : startNodes) {
    cache.setNodeIfNotExist(node);
  }
}
