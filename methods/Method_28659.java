@Override public byte[] getSet(final byte[] key,final byte[] value){
  return new JedisClusterCommand<byte[]>(connectionHandler,maxRedirections){
    @Override public byte[] execute(    Jedis connection){
      return connection.getSet(key,value);
    }
  }
.runBinary(key);
}
