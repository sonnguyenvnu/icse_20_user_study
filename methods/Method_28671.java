@Override public Long geoadd(final byte[] key,final Map<byte[],GeoCoordinate> memberCoordinateMap){
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.geoadd(key,memberCoordinateMap);
    }
  }
.runBinary(key);
}
