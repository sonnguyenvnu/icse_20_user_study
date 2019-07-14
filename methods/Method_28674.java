@Override public List<byte[]> geohash(final byte[] key,final byte[]... members){
  return new JedisClusterCommand<List<byte[]>>(connectionHandler,maxRedirections){
    @Override public List<byte[]> execute(    Jedis connection){
      return connection.geohash(key,members);
    }
  }
.runBinary(key);
}
