public byte[] getBytes(final String key){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<byte[]>(connectionHandler,maxRedirections){
    @Override public byte[] execute(    Jedis connection){
      return connection.get(keyByte);
    }
  }
.runBinary(keyByte);
}
