@Override public Jedis getConnection(){
  List<JedisPool> pools=getShuffledNodesPool();
  for (  JedisPool pool : pools) {
    Jedis jedis=null;
    try {
      jedis=pool.getResource();
      if (jedis == null) {
        continue;
      }
      String result=jedis.ping();
      if (result.equalsIgnoreCase("pong"))       return jedis;
      pool.returnBrokenResource(jedis);
    }
 catch (    JedisConnectionException ex) {
      if (jedis != null) {
        pool.returnBrokenResource(jedis);
      }
    }
  }
  throw new JedisConnectionException("no reachable node in cluster");
}
