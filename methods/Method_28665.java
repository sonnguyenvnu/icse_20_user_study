@Override public String scriptFlush(final byte[] key){
  return new JedisClusterCommand<String>(connectionHandler,maxRedirections){
    @Override public String execute(    Jedis connection){
      return connection.scriptFlush();
    }
  }
.runBinary(key);
}
