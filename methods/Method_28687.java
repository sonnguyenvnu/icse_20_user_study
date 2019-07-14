@Override public List<GeoCoordinate> geopos(byte[] key,byte[]... members){
  Jedis j=getShard(key);
  return j.geopos(key,members);
}
