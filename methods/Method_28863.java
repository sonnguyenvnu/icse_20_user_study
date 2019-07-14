public Long setrange(final String key,final long offset,final byte[] value){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    public Long execute(    Jedis connection){
      return connection.setrange(keyByte,offset,value);
    }
  }
.runBinary(keyByte);
}
