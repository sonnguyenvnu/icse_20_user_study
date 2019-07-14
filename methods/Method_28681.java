@Override public Set<byte[]> smembers(byte[] key){
  Jedis j=getShard(key);
  return j.smembers(key);
}
