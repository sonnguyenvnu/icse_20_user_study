protected String getNodeKey(Jedis jedis){
  return jedis.getClient().getHost() + ":" + jedis.getClient().getPort();
}
