@Override public void activateObject(PooledObject<Jedis> pooledJedis) throws Exception {
  final BinaryJedis jedis=pooledJedis.getObject();
  if (jedis.getDB() != database) {
    jedis.select(database);
  }
}
