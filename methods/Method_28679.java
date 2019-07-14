@Override public Long hsetnx(byte[] key,byte[] field,byte[] value){
  Jedis j=getShard(key);
  return j.hsetnx(key,field,value);
}
