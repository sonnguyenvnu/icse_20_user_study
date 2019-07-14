@Override public Long hincrBy(byte[] key,byte[] field,long value){
  Jedis j=getShard(key);
  return j.hincrBy(key,field,value);
}
