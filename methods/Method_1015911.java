@Override public void remove(final Collection<String> keys){
  final List<String> cacheKeys=new ArrayList<>(keys.size());
  final String cacheName=getName();
  for (  final String key : keys) {
    cacheKeys.add(cacheName + key);
  }
  Jedis jedis=null;
  try {
    jedis=Connections.getJedis();
    jedis.del(cacheKeys.toArray(new String[]{}));
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Remove data to cache with keys [" + keys + "] failed",e);
  }
 finally {
    if (null != jedis) {
      jedis.close();
    }
  }
}
