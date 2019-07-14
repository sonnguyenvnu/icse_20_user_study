@Override public List<GeoCoordinate> geopos(final byte[] key,final byte[]... members){
  return new JedisClusterCommand<List<GeoCoordinate>>(connectionHandler,maxRedirections){
    @Override public List<GeoCoordinate> execute(    Jedis connection){
      return connection.geopos(key,members);
    }
  }
.runBinary(key);
}
