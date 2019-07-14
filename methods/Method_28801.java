@Override public void destroyObject(PooledObject<Jedis> pooledJedis) throws Exception {
  final BinaryJedis jedis=pooledJedis.getObject();
  if (jedis.isConnected()) {
    try {
      try {
        jedis.quit();
      }
 catch (      Exception e) {
      }
      jedis.disconnect();
    }
 catch (    Exception e) {
    }
  }
}
