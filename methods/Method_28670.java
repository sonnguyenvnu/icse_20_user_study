@Override public Long geoadd(final byte[] key,final double longitude,final double latitude,final byte[] member){
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.geoadd(key,longitude,latitude,member);
    }
  }
.runBinary(key);
}
