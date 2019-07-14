public byte[] lindexBytes(final String key,final long index){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<byte[]>(connectionHandler,maxRedirections){
    public byte[] execute(    Jedis connection){
      return connection.lindex(keyByte,index);
    }
  }
.runBinary(keyByte);
}
