private JedisPool maintainJedisPool(String host,int port,String password){
  String hostAndPort=ObjectConvert.linkIpAndPort(host,port);
  JedisPool jedisPool=jedisPoolMap.get(hostAndPort);
  if (jedisPool == null) {
    lock.lock();
    try {
      jedisPool=jedisPoolMap.get(hostAndPort);
      if (jedisPool == null) {
        try {
          if (StringUtils.isNotBlank(password)) {
            jedisPool=new JedisPool(new GenericObjectPoolConfig(),host,port,Protocol.DEFAULT_TIMEOUT,password);
          }
 else {
            jedisPool=new JedisPool(new GenericObjectPoolConfig(),host,port,Protocol.DEFAULT_TIMEOUT);
          }
          jedisPoolMap.put(hostAndPort,jedisPool);
        }
 catch (        Exception e) {
          logger.error(e.getMessage(),e);
        }
 finally {
        }
      }
    }
  finally {
      lock.unlock();
    }
  }
  return jedisPool;
}
