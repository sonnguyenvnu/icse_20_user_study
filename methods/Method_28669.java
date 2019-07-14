@Override public Long bitop(final BitOP op,final byte[] destKey,final byte[]... srcKeys){
  byte[][] wholeKeys=KeyMergeUtil.merge(destKey,srcKeys);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.bitop(op,destKey,srcKeys);
    }
  }
.runBinary(wholeKeys.length,wholeKeys);
}
