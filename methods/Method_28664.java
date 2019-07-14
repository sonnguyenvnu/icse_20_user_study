@Override public byte[] scriptLoad(final byte[] script,final byte[] key){
  return new JedisClusterCommand<byte[]>(connectionHandler,maxRedirections){
    @Override public byte[] execute(    Jedis connection){
      return connection.scriptLoad(script);
    }
  }
.runBinary(key);
}
