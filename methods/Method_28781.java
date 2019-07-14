@Override public List<GeoCoordinate> geopos(final String key,final String... members){
  return new JedisClusterCommand<List<GeoCoordinate>>(connectionHandler,maxRedirections){
    @Override public List<GeoCoordinate> execute(    Jedis connection){
      return connection.geopos(key,members);
    }
  }
.run(key);
}
