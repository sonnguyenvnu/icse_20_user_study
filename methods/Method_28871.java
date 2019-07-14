public Double zscore(final String key,final byte[] member){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Double>(connectionHandler,maxRedirections){
    public Double execute(    Jedis connection){
      return connection.zscore(keyByte,member);
    }
  }
.runBinary(keyByte);
}
