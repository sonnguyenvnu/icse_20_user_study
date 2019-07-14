public Long hsetnx(final String key,final String field,final byte[] value){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    public Long execute(    Jedis connection){
      return connection.hsetnx(keyByte,SafeEncoder.encode(field),value);
    }
  }
.runBinary(keyByte);
}
