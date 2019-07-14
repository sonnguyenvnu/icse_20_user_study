@Override public Collection<byte[]> hvals(final byte[] key){
  return new JedisClusterCommand<Collection<byte[]>>(connectionHandler,maxRedirections){
    @Override public Collection<byte[]> execute(    Jedis connection){
      return connection.hvals(key);
    }
  }
.runBinary(key);
}
