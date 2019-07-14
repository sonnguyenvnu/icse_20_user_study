@Override public Long geoadd(byte[] key,Map<byte[],GeoCoordinate> memberCoordinateMap){
  Jedis j=getShard(key);
  return j.geoadd(key,memberCoordinateMap);
}
