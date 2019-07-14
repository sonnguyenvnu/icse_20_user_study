public Set<byte[]> smembersBytes(final String key){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Set<byte[]>>(connectionHandler,maxRedirections){
    public Set<byte[]> execute(    Jedis connection){
      return connection.smembers(keyByte);
    }
  }
.runBinary(keyByte);
}
