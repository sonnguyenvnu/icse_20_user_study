@Override public Double hincrByFloat(final byte[] key,final byte[] field,final double value){
  return new JedisClusterCommand<Double>(connectionHandler,maxRedirections){
    @Override public Double execute(    Jedis connection){
      return connection.hincrByFloat(key,field,value);
    }
  }
.runBinary(key);
}
