@Override public Long zrevrank(final byte[] key,final byte[] member){
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.zrevrank(key,member);
    }
  }
.runBinary(key);
}
