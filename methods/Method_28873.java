public Long lpushx(final String key,final byte[]... string){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    public Long execute(    Jedis connection){
      return connection.lpushx(keyByte,string);
    }
  }
.runBinary(keyByte);
}
