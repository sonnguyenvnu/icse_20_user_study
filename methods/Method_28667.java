@Override public Long sinterstore(final byte[] dstkey,final byte[]... keys){
  byte[][] wholeKeys=KeyMergeUtil.merge(dstkey,keys);
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.sinterstore(dstkey,keys);
    }
  }
.runBinary(wholeKeys.length,wholeKeys);
}
