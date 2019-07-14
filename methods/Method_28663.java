@Override public Set<byte[]> zrevrangeByLex(final byte[] key,final byte[] max,final byte[] min){
  return new JedisClusterCommand<Set<byte[]>>(connectionHandler,maxRedirections){
    @Override public Set<byte[]> execute(    Jedis connection){
      return connection.zrangeByLex(key,max,min);
    }
  }
.runBinary(key);
}
