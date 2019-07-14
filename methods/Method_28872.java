public Long linsert(final String key,final BinaryClient.LIST_POSITION where,final byte[] pivot,final byte[] value){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    public Long execute(    Jedis connection){
      return connection.linsert(keyByte,where,pivot,value);
    }
  }
.runBinary(keyByte);
}
