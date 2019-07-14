public List<byte[]> hvalsBytes(final String key){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<List<byte[]>>(connectionHandler,maxRedirections){
    public List<byte[]> execute(    Jedis connection){
      return connection.hvals(keyByte);
    }
  }
.runBinary(keyByte);
}
