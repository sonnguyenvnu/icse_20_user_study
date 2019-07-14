@Override public Long geoadd(final String key,final Map<String,GeoCoordinate> memberCoordinateMap){
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.geoadd(key,memberCoordinateMap);
    }
  }
.run(key);
}
