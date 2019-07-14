@Override public Long zrevrank(final String key,final String member){
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.zrevrank(key,member);
    }
  }
.run(key);
}
