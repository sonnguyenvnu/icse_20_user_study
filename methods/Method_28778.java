@Override public Long publish(final String channel,final String message){
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.publish(channel,message);
    }
  }
.runWithAnyNode();
}
