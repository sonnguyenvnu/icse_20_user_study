@Override public Double geodist(final String key,final String member1,final String member2,final GeoUnit unit){
  return new JedisClusterCommand<Double>(connectionHandler,maxRedirections){
    @Override public Double execute(    Jedis connection){
      return connection.geodist(key,member1,member2,unit);
    }
  }
.run(key);
}
