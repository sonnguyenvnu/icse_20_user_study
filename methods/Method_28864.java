public byte[] getrangeBytes(final String key,final long startOffset,final long endOffset){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<byte[]>(connectionHandler,maxRedirections){
    public byte[] execute(    Jedis connection){
      return connection.getrange(keyByte,startOffset,endOffset);
    }
  }
.runBinary(keyByte);
}
