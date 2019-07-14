@Override public Double geodist(final byte[] key,final byte[] member1,final byte[] member2){
  return new JedisClusterCommand<Double>(connectionHandler,maxRedirections){
    @Override public Double execute(    Jedis connection){
      return connection.geodist(key,member1,member2);
    }
  }
.runBinary(key);
}
