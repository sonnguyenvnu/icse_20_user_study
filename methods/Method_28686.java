@Override public Double geodist(byte[] key,byte[] member1,byte[] member2,GeoUnit unit){
  Jedis j=getShard(key);
  return j.geodist(key,member1,member2,unit);
}
