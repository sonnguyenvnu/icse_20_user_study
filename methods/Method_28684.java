@Override public Long geoadd(byte[] key,double longitude,double latitude,byte[] member){
  Jedis j=getShard(key);
  return j.geoadd(key,longitude,latitude,member);
}
