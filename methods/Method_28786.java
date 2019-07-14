public Jedis getConnectionFromNode(HostAndPort node){
  cache.setNodeIfNotExist(node);
  return cache.getNode(JedisClusterInfoCache.getNodeKey(node)).getResource();
}
