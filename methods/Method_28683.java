@Override public long pfcount(final byte[] key){
  Jedis j=getShard(key);
  return j.pfcount(key);
}
