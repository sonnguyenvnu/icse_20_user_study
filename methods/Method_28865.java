public byte[] getSetBytes(final String key,final byte[] value){
  final byte[] keyByte=SafeEncoder.encode(key);
  return new JedisClusterCommand<byte[]>(connectionHandler,maxRedirections){
    public byte[] execute(    Jedis connection){
      return connection.getSet(keyByte,value);
    }
  }
.runBinary(keyByte);
}
