public void disconnect(){
  for (  Jedis jedis : getAllShards()) {
    try {
      jedis.quit();
    }
 catch (    JedisConnectionException e) {
    }
    try {
      jedis.disconnect();
    }
 catch (    JedisConnectionException e) {
    }
  }
}
