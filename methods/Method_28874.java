public Long rpushx(final String key,final byte[]... string){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    public Long execute(    Jedis connection){
      return connection.rpushx(keyByte,string);
    }
  }
.runBinary(keyByte);
}
