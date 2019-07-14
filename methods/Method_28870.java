public Long zrevrank(final String key,final byte[] member){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    public Long execute(    Jedis connection){
      return connection.zrevrank(keyByte,member);
    }
  }
.runBinary(keyByte);
}
