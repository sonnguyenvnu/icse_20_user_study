public String set(String key,String value){
  Jedis jedis=borrow();
  try {
    return jedis.set(key,value);
  }
  finally {
    revert(jedis);
  }
}
